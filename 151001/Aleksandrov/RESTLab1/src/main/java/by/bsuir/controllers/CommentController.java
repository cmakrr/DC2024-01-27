package by.bsuir.controllers;

import by.bsuir.dto.CommentRequestTo;
import by.bsuir.dto.CommentResponseTo;
import by.bsuir.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentResponseTo>> getComments(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortOrder) {
        return ResponseEntity.status(200).body(commentService.getComments(pageNumber, pageSize, sortBy, sortOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseTo> getComment(@PathVariable Long id) {
        return ResponseEntity.status(200).body(commentService.getCommentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CommentResponseTo> saveComment(@RequestBody CommentRequestTo comment) {
        CommentResponseTo savedComment = commentService.saveComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
    }

    @PutMapping()
    public ResponseEntity<CommentResponseTo> updateComment(@RequestBody CommentRequestTo comment) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(comment));
    }

    @GetMapping("/byIssue/{id}")
    public ResponseEntity<List<CommentResponseTo>> getEditorByIssueId(@PathVariable Long id) {
        return ResponseEntity.status(200).body(commentService.getCommentByIssueId(id));
    }
}
