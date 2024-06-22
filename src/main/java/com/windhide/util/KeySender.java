package com.windhide.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class KeySender {
    static {
        // Load the appropriate DLL from the JAR based on the system architecture
        if (is64Bit()) {
            loadLibraryFromJar("/libs/KeySender.dll");
        } else {
            loadLibraryFromJar("/libs/KeySender_x86.dll");
        }
    }

    private native void sendKey(int hwnd, int key);

    public void sendKeyToWindow(int hwnd, int key) {
        sendKey(hwnd, key);
    }

    private native int findWindow(String partialName);

    public int getWindowHandle(String partialName) {
        return findWindow(partialName);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java KeySender <partialWindowName> <key>");
            return;
        }

        String partialWindowName = args[0];  // Partial window name
        int key = Integer.parseInt(args[1]); // Virtual key code

        KeySender keySender = new KeySender();
        int hwnd = keySender.getWindowHandle(partialWindowName);

        if (hwnd != 0) {
            keySender.sendKeyToWindow(hwnd, key);
        } else {
            System.out.println("Window not found with name containing: " + partialWindowName);
        }
    }

    private static void loadLibraryFromJar(String path) {
        try {
            // Extract the DLL file from the JAR
            InputStream in = KeySender.class.getResourceAsStream(path);
            if (in == null) {
                throw new RuntimeException("Library " + path + " not found in JAR.");
            }

            // Create a temporary file
            File tempFile = Files.createTempFile("lib", ".dll").toFile();
            tempFile.deleteOnExit();

            // Copy the DLL to the temporary file
            try (FileOutputStream out = new FileOutputStream(tempFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            // Load the library
            System.load(tempFile.getAbsolutePath());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load library", e);
        }
    }

    private static boolean is64Bit() {
        String arch = System.getProperty("os.arch");
        return arch.contains("64");
    }
}

