package ru.patterns.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patterns.interfaces.CanvasComponent;
import ru.patterns.models.CanvasFolder;
import ru.patterns.services.CanvasFolderService;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class CanvasFolderController {

    private final CanvasFolderService canvasFolderService;

    public CanvasFolderController(CanvasFolderService canvasFolderService) {
        this.canvasFolderService = canvasFolderService;
    }

    @PostMapping
    public ResponseEntity<CanvasFolder> createFolder(@RequestParam String name) {
        CanvasFolder folder = canvasFolderService.createFolder(name);
        return ResponseEntity.status(HttpStatus.CREATED).body(folder);
    }

    @PostMapping("/{folderId}/addCanvas/{canvasId}")
    public ResponseEntity<CanvasFolder> addCanvasToFolder(@PathVariable Long folderId,
                                                          @PathVariable Long canvasId) {
        CanvasFolder folder = canvasFolderService.addCanvasToFolder(folderId, canvasId);
        return ResponseEntity.status(HttpStatus.OK).body(folder);
    }

    @PostMapping("/{folderId}/addFolder/{childFolderId}")
    public ResponseEntity<CanvasFolder> addFolderToFolder(@PathVariable Long folderId,
                                                          @PathVariable Long childFolderId) {
        CanvasFolder folder = canvasFolderService.addFolderToFolder(folderId, childFolderId);
        return ResponseEntity.status(HttpStatus.OK).body(folder);
    }

    @GetMapping("/{folderId}/contents")
    public ResponseEntity<List<CanvasComponent>> getFolderContents(@PathVariable Long folderId) {
        List<CanvasComponent> contents = canvasFolderService.getFolderContents(folderId);
        return ResponseEntity.ok(contents);
    }
}
