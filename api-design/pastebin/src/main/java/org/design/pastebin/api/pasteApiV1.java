package org.design.pastebin.api;

import org.design.pastebin.dto.PasteDTO;

public interface pasteApiV1 {
    void addPaste(PasteDTO data, Long userId);
    String getPaste( String alias);
    void deletePaste(String alias, Long userId);
}
