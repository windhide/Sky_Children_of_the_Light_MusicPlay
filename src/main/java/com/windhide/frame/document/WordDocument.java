package com.windhide.frame.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class WordDocument extends PlainDocument {

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if ((getLength() + str.length()) <= 1) {
            char[] origin = str.toCharArray();
            char[] before = new char[]{origin[origin.length - 1]};
            super.insertString(offset, new String(before, 0, before.length), attr);
        }
    }
}
