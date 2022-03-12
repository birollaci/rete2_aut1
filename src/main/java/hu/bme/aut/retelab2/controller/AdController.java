package hu.bme.aut.retelab2.controller;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdRepository adRepository;

    @GetMapping
    public List<Ad> getAll(@RequestParam(required = false, defaultValue = "0") String min, @RequestParam(required = false, defaultValue = "10000000") String max) {
        List<Ad> res =  adRepository.findByPrice(Integer.parseInt(min), Integer.parseInt(max));
        for (int i = 0; i < res.size(); i++) {
            if(res.get(i) != null) {
                res.get(i).setKod("000000");
            }
        }
        return res;
    }



    @PostMapping
    public Ad create(@RequestBody Ad ad) {
        ad.setId(null);
        ad.setKod(generate());
        return adRepository.save(ad);
    }

    @PutMapping
    public ResponseEntity<Ad> update(@RequestBody Ad ad) {
        Ad n = adRepository.findById(ad.getId());
        if (n == null)
            return ResponseEntity.notFound().build();
        if (!n.getKod().equals(ad.getKod())) {
            return (ResponseEntity<Ad>) ResponseEntity.status(HttpStatus.FORBIDDEN);
        } else {
            n = adRepository.save(ad);
            return ResponseEntity.ok(ad);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Ad ad = adRepository.findById(id);
        if (ad == null)
            return ResponseEntity.notFound().build();
        else {
            adRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    // 4. feladat
    @GetMapping(path = "{tag}")
    public List<Ad> getTagParam( @PathVariable("tag") String tag) {
        List<Ad> res =  adRepository.findByTag(tag);

        for (int i = 0; i < res.size(); i++) {
            if(res.get(i) != null) {
                res.get(i).setKod("000000");
            }
        }
        return res;
    }

    // Secret code generator
    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RND = new Random();

    public static String generate() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }
}