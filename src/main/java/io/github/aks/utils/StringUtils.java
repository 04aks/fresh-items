package io.github.aks.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class StringUtils {
    public static String getClipboard() throws IOException, UnsupportedFlavorException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable transferable = clipboard.getContents(null);
        String text = "";
        if(transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            text = (String) transferable.getTransferData(DataFlavor.stringFlavor);
        }

        return text;
    }
}
