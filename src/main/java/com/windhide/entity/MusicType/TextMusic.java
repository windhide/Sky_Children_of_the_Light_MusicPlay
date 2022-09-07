package com.windhide.entity.MusicType;

import lombok.Data;

import java.util.List;

@Data
public class TextMusic {
     private String name;
     private String author;
     private String transcribedBy;
     private String isComposed;
     private String bpm;
     private String bitsPerPage;
     private String pitchLevel;
     private String isEncrypted;
     private List<TextMusicNotes> songNotes;
}
