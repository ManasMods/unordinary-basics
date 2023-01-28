package com.github.manasmods.unordinary_basics.client.keybind;

import com.github.manasmods.manascore.api.client.keybinding.KeybindingCategory;
import com.github.manasmods.manascore.api.client.keybinding.ManasKeybinding;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestPlayerProneToggle;
import com.mojang.blaze3d.platform.InputConstants;

public class Keybindings {

    private static final KeybindingCategory UNORDINARY_BASICS = KeybindingCategory.of("unordinary_basics");

    public static final ManasKeybinding SET_PRONE = new ManasKeybinding("unordinary_basics.keybinding.set_prone", InputConstants.KEY_X, UNORDINARY_BASICS, () -> Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestPlayerProneToggle()));

}
