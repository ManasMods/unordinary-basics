package com.github.manasmods.unordinary_basics.client.keybind;

import com.github.manasmods.manascore.api.client.keybinding.KeybindingCategory;
import com.github.manasmods.manascore.api.client.keybinding.ManasKeybinding;
import com.github.manasmods.unordinary_basics.network.Unordinary_BasicsNetwork;
import com.github.manasmods.unordinary_basics.network.toserver.RequestUBInventoryMenu;
import com.mojang.blaze3d.platform.InputConstants;

public class UBKeybindings {

    private static final KeybindingCategory UNORDINARY_BASICS = KeybindingCategory.of("unordinary_basics");

    public static final ManasKeybinding OPEN_UB_INV = new ManasKeybinding("unordinary_basics.keybinding.open_ub_inv", InputConstants.KEY_I, UNORDINARY_BASICS, () -> Unordinary_BasicsNetwork.getInstance().sendToServer(new RequestUBInventoryMenu()));

}
