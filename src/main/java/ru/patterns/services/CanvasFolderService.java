package ru.patterns.services;

import org.springframework.stereotype.Service;
import ru.patterns.interfaces.CanvasComponent;
import ru.patterns.models.Canvas;
import ru.patterns.models.CanvasFolder;
import ru.patterns.repositories.CanvasFolderRepository;
import ru.patterns.repositories.CanvasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CanvasFolderService {

    private final CanvasFolderRepository canvasFolderRepository;
    private final CanvasRepository canvasRepository;

    public CanvasFolderService(CanvasFolderRepository canvasFolderRepository, CanvasRepository canvasRepository) {
        this.canvasFolderRepository = canvasFolderRepository;
        this.canvasRepository = canvasRepository;
    }

    public CanvasFolder createFolder(String name) {
        CanvasFolder folder = new CanvasFolder(name);
        return canvasFolderRepository.save(folder);
    }

    public CanvasFolder addCanvasToFolder(Long folderId, Long canvasId) {
        Optional<CanvasFolder> folderOptional = canvasFolderRepository.findById(folderId);
        Optional<Canvas> canvasOptional = canvasRepository.findById(canvasId);

        if (folderOptional.isPresent() && canvasOptional.isPresent()) {
            CanvasFolder folder = folderOptional.get();
            Canvas canvas = canvasOptional.get();
            folder.add(canvas);
            return canvasFolderRepository.save(folder);
        } else {
            throw new RuntimeException("Folder or Canvas not found");
        }
    }

    public CanvasFolder addFolderToFolder(Long parentFolderId, Long childFolderId) {
        Optional<CanvasFolder> parentFolderOptional = canvasFolderRepository.findById(parentFolderId);
        Optional<CanvasFolder> childFolderOptional = canvasFolderRepository.findById(childFolderId);

        if (parentFolderOptional.isPresent() && childFolderOptional.isPresent()) {
            CanvasFolder parentFolder = parentFolderOptional.get();
            CanvasFolder childFolder = childFolderOptional.get();
            parentFolder.add(childFolder);
            return canvasFolderRepository.save(parentFolder);
        } else {
            throw new RuntimeException("Parent or Child Folder not found");
        }
    }

    public List<CanvasComponent> getFolderContents(Long folderId) {
        Optional<CanvasFolder> folderOptional = canvasFolderRepository.findById(folderId);
        if (folderOptional.isPresent()) {
            return folderOptional.get().getChildren();
        } else {
            throw new RuntimeException("Folder not found");
        }
    }
}
