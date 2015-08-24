package com.timthebrick.tinystorage.common.reference;

import com.timthebrick.tinystorage.client.gui.widgets.settings.AccessMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.BooleanMode;
import com.timthebrick.tinystorage.client.gui.widgets.settings.ButtonSettings;

public class Messages {

    public static final class Chat {
        public static final String OWNER_SET_TO_SELF = "general.misc.tinyStorage:setOwner";
        public static final String CHEST_NOT_OWNED = "general.blocks.chests.notOwned";
        public static final String PIGGY_BANK_NOT_OWNED = "general.blocks.piggyBank.notOwned";
        public static final String PIGGY_BANK_FULL = "general.blocks.piggyBank.full";
        public static final String PIGGY_BANK_EMPTY = "general.blocks.piggyBank.empty";
        public static final String PIGGY_BANK_CURRENT_CONTENTS = "general.blocks.piggyBank.contents";
        public static final String QUARRY_CHEST_DEPTH = "general.blocks.quarryChest.depth";
        public static final String QUARRY_CHEST_DEPTH_BLOCKS = "general.blocks.quarryChest.depthBlocks";
        public static final String MOD_UPDATE = "update.tinystorage:update";
        public static final String MOD_VERSION = "update.tinystorage:new_version";
        public static final String MOD_DOWNLOAD = "update.tinystorage:download";
        public static final String DEBUG_TOOL_MODE = "tooltip.tinystorage:debugTool.mode";
        public static final String DEBUG_TOOL_CASE = "tooltip.tinystorage:debugTool.case";
        public static final String UNFRIENDED = "chat.tinystorage:unfriended";
        public static final String FRIEND_SETTER_MODE = "tooltip.tinystorage:friendSetter.mode";
        public static final String FRIEND_SETTER_CASE = "tooltip.tinystorage:friendSetter.case";
    }

    public static final class ItemTooltips {
        public static final String ITEM_TIER_1 = "tooltip.tinystorage:itemSize.small";
        public static final String ITEM_TIER_2 = "tooltip.tinystorage:itemSize.medium";
        public static final String ITEM_TIER_3 = "tooltip.tinystorage:itemSize.large";
        public static final String ITEM_SPECIAL_1 = "tooltip.tinystorage:itemSize.unstable";
        public static final String ITEM_SPECIAL_2 = "tooltip.tinystorage:itemSize.tiny";
        public static final String BLOCK_SMALL = "tooltip.tinystorage:blockSize.small";
        public static final String BLOCK_MEDIUM = "tooltip.tinystorage:blockSize.medium";
        public static final String BLOCK_LARGE = "tooltip.tinystorage:blockSize.large";
        public static final String BLOCK_LOCKED = "tooltip.tinystorage:blockLocked";
        public static final String PEACEFUL_CHEST_DESC_1 = "tooltip.tinystorage:peacefulChest.description1";
        public static final String PEACEFUL_CHEST_DESC_2 = "tooltip.tinystorage:peacefulChest.description2";
        public static final String QUARRY_CHEST_DESC_1 = "tooltip.tinystorage:quarryChestPrefix.mode";
        public static final String QUARRY_CHEST_DESC_2 = "tooltip.tinystorage:quarryChestPrefix.modeA";
        public static final String DEBUG_TOOL_MODE_TIP_1 = "tooltip.tinystorage:debugTool.modetipA";
        public static final String DEBUG_TOOL_MODE_TIP_2 = "tooltip.tinystorage:debugTool.modetipB";
        public static final String DEBUG_TOOL_MODE = "tooltip.tinystorage:debugTool.mode";
        public static final String DEBUG_TOOL_CASE = "tooltip.tinystorage:debugTool.case";
        public static final String IMPOSSIBLE_CHEST = "tooltip.tinystorage:impossibleChest";
        public static final String FRIEND_SETTER_MODE_TIP_1 = "tooltip.tinystorage:friendSetter.modetipA";
        public static final String FRIEND_SETTER_MODE_TIP_2 = "tooltip.tinystorage:friendSetter.modetipB";
        public static final String FRIEND_SETTER_MODE = "tooltip.tinystorage:friendSetter.mode";
        public static final String FRIEND_SETTER_CASE = "tooltip.tinystorage:friendSetter.case";
    }

    public static final class Config {
        public static final String SOUND_MODE = "general.sound.soundMode";
        public static final String SOUND_MODE_LABEL = "general.sound.soundMode.label";
        public static final String SOUND_MODE_COMMENT = "general.sound.soundMode.comment";
        public static final String PEACEFUL_CHEST_MODE = "general.blocks.peacefulChestMode";
        public static final String PEACEFUL_CHEST_LABEL = "general.blocks.peacefulChestMode.label";
        public static final String PEACEFUL_CHEST_COMMENT = "general.blocks.peacefulChestMode.comment";
        public static final String VERSION_CHECK_MODE = "misc.versionCheckMode";
        public static final String VERSION_CHECK_LABEL = "general.misc.versionCheckMode.label";
        public static final String VERSION_CHECK_COMMENT = "general.misc.versionCheckMode.comment";

    }

    public static final class ButtonTooltips {
        public static final String ACCESS_MODE_TITLE = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase();
        public static final String ACCESS_MODE_BLOCKED = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.DISABLED.toString().toLowerCase();
        public static final String ACCESS_MODE_INPUT_ONLY = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_ONLY.toString().toLowerCase();
        public static final String ACCESS_MODE_OUTPUT_ONLY = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.OUTPUT_ONLY.toString().toLowerCase();
        public static final String ACCESS_MODE_BOTH = "button.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_OUTPUT.toString().toLowerCase();
        public static final String DELETE_STACK_TITLE = "button.tooltip.tinystorage:" + ButtonSettings.DELETE_LAST_STACK.toString().toLowerCase();
        public static final String DELETE_STACK_TRUE = "button.tooltip.tinystorage:" + ButtonSettings.DELETE_LAST_STACK.toString().toLowerCase() + "_" + BooleanMode.TRUE.toString().toLowerCase();
        public static final String DELETE_STACK_FALSE = "button.tooltip.tinystorage:" + ButtonSettings.DELETE_LAST_STACK.toString().toLowerCase() + "_" + BooleanMode.FALSE.toString().toLowerCase();
        public static final String ADD = "button.tooltip.tinystorage:" + ButtonSettings.ADD.toString().toLowerCase();
        public static final String DELETE = "button.tooltip.tinystorage:" + ButtonSettings.DELETE.toString().toLowerCase();
        public static final String UP = "button.tooltip.tinystorage:" + ButtonSettings.UP.toString().toLowerCase();
        public static final String DOWN = "button.tooltip.tinystorage:" + ButtonSettings.DOWN.toString().toLowerCase();
    }

    public static final class WailaTooltips {
        public static final String ACCESS_MODE_TITLE = "waila.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase();
        public static final String ACCESS_MODE_BLOCKED = "waila.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.DISABLED.toString().toLowerCase();
        public static final String ACCESS_MODE_INPUT_ONLY = "waila.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_ONLY.toString().toLowerCase();
        public static final String ACCESS_MODE_OUTPUT_ONLY = "waila.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.OUTPUT_ONLY.toString().toLowerCase();
        public static final String ACCESS_MODE_BOTH = "waila.tooltip.tinystorage:" + ButtonSettings.AUTOMATED_SIDE_ACCESS.toString().toLowerCase() + "_" + AccessMode.INPUT_OUTPUT.toString().toLowerCase();
        public static final String CHEST_NOT_OWNED = "waila.tooltip.tinystorage:chestNotOwned";
        public static final String BLOCK_SMALL = "waila.tooltip.tinystorage:blockSize.small";
        public static final String BLOCK_MEDIUM = "waila.tooltip.tinystorage:blockSize.medium";
        public static final String BLOCK_LARGE = "waila.tooltip.tinystorage:blockSize.large";
    }

    public static final class WidgetTooltips {
        public static final String FRIENDS_LIST = "widget.tooltip.tinystorage:friendsList";
    }

    public static final class GuiLabels {
        public static final String FRIENDS_LIST = "gui.label.tinystorage:search";
    }
}
