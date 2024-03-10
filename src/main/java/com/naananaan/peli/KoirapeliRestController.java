package com.naananaan.peli;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.naananaan.peli.Harrastus.HarrastusType;
import com.naananaan.peli.Herkut.HerkutType;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Random;

@RestController

public class KoirapeliRestController {


    private Koira game;
    private KoiranAsiat koiranAsiat;

    private boolean checkBudjetti(Harrastus oldHarrastus, Harrastus updatedHarrastus) {
        double budjetti = koiranAsiat.getBudjetti();
        double oldMenot = oldHarrastus.getMenot(game);
        double updatedMenot = updatedHarrastus.getMenot(game);
        return budjetti - oldMenot + updatedMenot >= 0;
    }

    @GetMapping("/")
    public String info() {
        return "******Koirapeli*******<br><br>\n\n" +
        "----- (info) - peli selitettynä <br>\n";
        // valitse koirarotu
        // valitse nimi
        // valitse sopivat valjaat ja hihna ulkoilu
        // valitse ruoka- ja juomakipot ruokailu
        // asiat vähenee valintojen myötä



    }

    @PostMapping("/aloita")
    public String startGame(@RequestBody StartGameRequest request) {
        this.game = new Koira(String.valueOf(request.rotu()), String.valueOf(request.koiranNimi()), request.ulkoilu(), request.budjetti(), 0.0);
        this.koiranAsiat = new KoiranAsiat(game, request.budjetti());
        return "Valitsit rodun: " + game.getRotu() + ". Annoit hänelle nimen : " + game.getKoiranNimi() + "ja sille valitsit " + game.getUlkoilu() + " valjaat ja hihnan. Nyt pääset hoitamaan koiraasi! Sinulla on käytettävissä: " + koiranAsiat.getBudjetti() + "euroa.";
    }

    
    @GetMapping("/koirani")
    public double getKoiraniMinun() {
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not started");
        }
        return game.getMinun();
    }
    @GetMapping("/budjetti")
    public double getBudjetti() {
        return koiranAsiat.getBudjetti() - koiranAsiat.getKoira().getMenot(); 
    }
    @GetMapping("/herkut") 
    public List <Herkut> getHerkut() {
        return koiranAsiat.getKoira().getHerkuts();
    }
    @GetMapping("/harrastus") 
    public Harrastus getHarrastus() {
        return koiranAsiat.getKoira().getHarrastus();
    }

    @GetMapping("/koira")
    public Koira getKoira() {
    return koiranAsiat.getKoira(); 
}

@PutMapping("/herkut/{id}") 
public void updateHerkut(@PathVariable Integer id, @RequestBody String type) {
    HerkutType newType = HerkutType.valueOf(type.trim());
    Herkut updatedHerkut = new Herkut(koiranAsiat.getKoira().getHerkuts().get(id).getMinun(), newType);
    if (checkBudget(koiranAsiat.getKoira().getHerkuts().get(id), updatedHerkut)) {
        koiranAsiat.getKoira().updateHerkut(koiranAsiat.getKoira().getHerkuts(), id, newType);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Budjetti ei riitä tähän herkkuun");
    }
}

@PutMapping("/harrastus") 
public void updateHarrastus(@RequestBody String type) {
    HarrastusType newType = HarrastusType.valueOf(type.trim());
    Harrastus updatedHarrastus = new Harrastus(newType);
    if (checkBudjetti(koiranAsiat.getKoira().getHarrastus(), updatedHarrastus)) {
        koiranAsiat.getKoira().updateHarrastus(newType);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Budjetti ei riitä tähän harrastukseen");
    }
}

@GetMapping("/herkut/types")
    public List<HerkutType> getHerkutTypes() {
        return koiranAsiat.getAvailableHerkutTypes();
    }

    @GetMapping("/harrastus/types")
    public List<HarrastusType> getHarrastusTypes() {
        return koiranAsiat.getAvailableHarrastusTypes();
    }

    private boolean checkBudget(ValitutTuotteet oldHerkut, ValitutTuotteet updatedHerkut) {
        double budjetti = koiranAsiat.getBudjetti();
        budjetti -= koiranAsiat.getKoira().getMenot();

        double delta = updatedHerkut.getMenot(koiranAsiat.getKoira()) - oldHerkut.getMenot(koiranAsiat.getKoira());
        budjetti -= delta;
        return budjetti >= 0;
    }

}