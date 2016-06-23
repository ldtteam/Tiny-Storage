package com.smithsmodding.tinystorage.api.reference;

/**
 * Created by Tim on 19/06/2016.
 */
public class References {

    public static final String MOD_ID = "TinyStorage";
    public static final String MOD_NAME = "Tiny Storage";
    public static final String VERSION = "@VERSION@";
    public static final String API_VERSION = "@APIVERSION@";
    public static final String SERVER_PROXY_CLASS = "com.smithsmodding.tinystorage.common.proxy.ServerProxy";
    public static final String CLIENT_PROXY_CLASS = "com.smithsmodding.tinystorage.common.proxy.ClientProxy";

    public static final String TE = "TinyStorage.TE.General";

    public static class Modules {

        public static class Storage {
            public static final int SMALLSIZE = 7;
            public static final int MEDIUMSIZE = 14;
            public static final int LARGESIZE = 21;
        }
    }
}
