package sia.tacocloud.controllers;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.objects.Taco;
import sia.tacocloud.repositories.TacoRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {
    private TacoRepository tacoRepository;

    public DesignTacoRestController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        return optionalTaco.orElse(null);
    }
}
