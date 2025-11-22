package com.projectB;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/products")
public class ProductController {

    private Map<Long, Map<String, Object>> db = new HashMap<>();
    private Long sequence = 1L;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
        body.put("id", sequence++);
        db.put((Long) body.get("id"), body);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(db.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        if (!db.containsKey(id)) {
            return ResponseEntity.status(404).body("Não encontrado");
        }
        return ResponseEntity.ok(db.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        if (!db.containsKey(id)) {
            return ResponseEntity.status(404).body("Não encontrado");
        }
        body.put("id", id);
        db.put(id, body);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        db.remove(id);
        return ResponseEntity.ok("Deletado");
    }
}
