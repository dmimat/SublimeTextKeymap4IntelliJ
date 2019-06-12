package com.intellij.keymaps;

import com.intellij.openapi.keymap.impl.BundledKeymapProvider;
import com.intellij.openapi.util.SystemInfo;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * @author Dmitry Matveev
 */
public class SublimeTextKeymap implements BundledKeymapProvider {
    @NotNull
    @Override
    public List<String> getKeymapFileNames() {
        return Collections.singletonList("Sublime Text.xml");
    }

    @Override
    public <R> R load(@NotNull String key, @NotNull Function<? super InputStream, ? extends R> consumer) throws IOException {
        String os = SystemInfo.isMac ? "macos" : SystemInfo.isWindows ? "windows" : "linux";
        try (InputStream stream = SublimeTextKeymap.class.getResourceAsStream("/keymaps/" + os + "/" + key)) {
            return consumer.apply(stream);
        }
    }
}
