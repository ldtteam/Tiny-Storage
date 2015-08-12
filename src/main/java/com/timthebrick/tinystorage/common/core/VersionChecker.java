package com.timthebrick.tinystorage.common.core;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.config.Property;

import com.timthebrick.tinystorage.TinyStorage;
import com.timthebrick.tinystorage.common.handler.ConfigurationHandler;
import com.timthebrick.tinystorage.common.reference.References;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInterModComms;

public class VersionChecker implements Runnable {

    public enum EnumUpdateState {
        CURRENT, OUTDATED, CONNECTION_ERROR
    }

    private static final String VERSION = "@VERSION@";
    private static EnumUpdateState currentVersion = EnumUpdateState.CURRENT;

    private static final String REMOTE_VERSION_FILE_RELEASE = "https://raw.githubusercontent.com/Tim020/Tiny-Storage/master/src/main/resources/versionsRelease";
    private static String remoteLocation = REMOTE_VERSION_FILE_RELEASE;

    private static String recommendedVersion;

    private static VersionChecker instance = new VersionChecker();

    private static boolean sentIMCOutdatedMessage = false;

    private static String getVersion() {
        return VERSION;
    }

    private static boolean isOutdated() {
        return currentVersion == EnumUpdateState.OUTDATED;
    }

    public static boolean needsUpdateNoticeAndMarkAsSeen() {
        if (!isOutdated() || sentIMCOutdatedMessage) {
            return false;
        }
        Property property = ConfigurationHandler.config.get("vars", "version.seen", VERSION);
        property.comment = "indicates the last version the user has been informed about and will suppress further notices on it.";
        String seenVersion = property.getString();
        if (recommendedVersion == null || recommendedVersion.equals(seenVersion)) {
            return false;
        }
        property.set(recommendedVersion);
        ConfigurationHandler.config.save();
        return true;
    }

    public static String getRecommendedVersion() {
        return recommendedVersion;
    }

    private static void versionCheck() {
        try {
            if ("0.0.0".equals(VERSION)) {
                return;
            }
            String version = VERSION;
            if (VERSION.contains("-Dev")) {
                String verTokens[] = VERSION.split("-");
                version = verTokens[0];
            }
            String location = remoteLocation;
            HttpURLConnection conn = null;
            while (location != null && !location.isEmpty()) {
                URL url = new URL(location);
                if (conn != null) {
                    conn.disconnect();
                }
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
                conn.connect();
                location = conn.getHeaderField("Location");
            }
            if (conn == null) {
                throw new NullPointerException();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            String mcVersion = TinyStorage.proxy.getMinecraftVersion();
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(":");
                if (mcVersion.matches(tokens[0])) {
                    if (References.MOD_ID.matches(tokens[1])) {
                        recommendedVersion = tokens[2];
                        if (version.split("_")[0].matches(recommendedVersion) && (!VERSION.contains("-Dev"))) {
                            TinyStorageLog.trace("Using the latest version [" + getVersion() + "] for Minecraft " + mcVersion);
                            currentVersion = EnumUpdateState.CURRENT;
                        }
                    }
                }
            }
            conn.disconnect();
            reader.close();
            if (currentVersion != EnumUpdateState.CURRENT) {
                TinyStorageLog.warn("Using outdated version [" + VERSION + "] for Minecraft " + mcVersion + ". Consider updating to " + recommendedVersion + ".");
                currentVersion = EnumUpdateState.OUTDATED;
                sendIMCOutdatedMessage();
            }
        } catch (Exception e) {
            TinyStorageLog.warn("Unable to read from remote version authority.");
            TinyStorageLog.warn(e.toString());
            currentVersion = EnumUpdateState.CONNECTION_ERROR;
        }
    }

    @Override
    public void run() {
        int count = 0;
        currentVersion = null;
        TinyStorageLog.info("Beginning version check");
        try {
            while ((count < 3) && ((currentVersion == null) || (currentVersion == EnumUpdateState.CONNECTION_ERROR))) {
                versionCheck();
                count++;
                if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
                    TinyStorageLog.info("Version check attempt " + count + " failed, trying again in 10 seconds");
                    Thread.sleep(10000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (currentVersion == EnumUpdateState.CONNECTION_ERROR) {
            TinyStorageLog.info("Version check failed");
        }
    }

    private static void sendIMCOutdatedMessage() {
        if (Loader.isModLoaded("VersionChecker")) {
            NBTTagCompound compound = new NBTTagCompound();
            compound.setString("modDisplayName", References.MOD_NAME);
            compound.setString("oldVersion", VERSION);
            compound.setString("newVersion", getRecommendedVersion());
            compound.setString("updateUrl", "http://minecraft.curseforge.com/mc-mods/227712-tiny-storage");
            compound.setBoolean("isDirectLink", false);
            FMLInterModComms.sendRuntimeMessage("TinyStorage", "VersionChecker", "addUpdate", compound);
            sentIMCOutdatedMessage = true;
        }
    }

    public static void check() {
        new Thread(instance).start();
    }

}
