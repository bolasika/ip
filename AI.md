# AI Usage 
## Overview
This documentation is to update the use of AI tools in this iP.

## Tools used
- GPT-5.2-Codex that's integrated into JetBrains IDEs.

## Increment #1 ( `branch-A-BetterGui` )
**Increment Overview:** Improving the GUI to make it more polished. 

**AI Usage:**
1. Make the GUI clearly distinguish the messages from user and Guma. This can be done by making
   - Green Bubble background for messages bubble from user 
   - Grey Bubble background for messages from Guma

**Code Segments edited by the Codex: **

In `src/main/resources/css/dialog-box.css`:
```css
.dialog-bubble {
    -fx-padding: 8px 10px 8px 10px;
    -fx-font-size: 12px;
    -fx-max-width: 280px;
    -fx-background-insets: 0;
    -fx-border-insets: 0;
    -fx-border-width: 1px;
    -fx-wrap-text: true;
}

.user-bubble {
    -fx-background-color: linear-gradient(to bottom right, #b6f7b1, #6ee7b7);
    -fx-text-fill: #114b30;
    -fx-border-color: #54b76a;
    -fx-background-radius: 14px 14px 0 14px;
    -fx-border-radius: 14px 14px 0 14px;
}

.guma-bubble {
    -fx-background-color: #e5e7eb;
    -fx-text-fill: #1f2937;
    -fx-border-color: #cbd5e1;
    -fx-background-radius: 14px 14px 14px 0;
    -fx-border-radius: 14px 14px 14px 0;
}
```

In `src/main/java/guma/DialogBox.java`:
```java
public static DialogBox getUserDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.dialog.getStyleClass().add("user-bubble");
    return db;
}

public static DialogBox getGumaDialog(String text, Image img) {
    var db = new DialogBox(text, img);
    db.dialog.getStyleClass().add("guma-bubble");
    db.flip();
    return db;
}
```

2. Make the GUI to highlight errors by making the message containing the errors to be RED.

**Pre-requisites:** Making all error messages to follow certain format, first 2 characters being `>>`

**Code Segments edited by the Codex**

In `src/main/java/guma/DialogBox.java`:
```java
 public static DialogBox getGumaDialog(String text, Image img) {
     var db = new DialogBox(text, img);
     db.dialog.getStyleClass().add("guma-bubble");
     if (text != null && text.startsWith(">>")) {
         db.dialog.getStyleClass().add("error-bubble");
     }
     db.flip();
     return db;
 }
```

In `src/main/resources/css/dialog-box.css`: 
```css
.error-bubble {
    -fx-background-color: #fee2e2;
    -fx-text-fill: #7f1d1d;
    -fx-border-color: #fca5a5;
}
```

**Interesting Observation:**
1. Saved a lot of time in designing and adjusting the visuals (e.g. padding / colour hex code)