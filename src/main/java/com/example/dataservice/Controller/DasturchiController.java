package com.example.dataservice.Controller;

import com.example.dataservice.Entity.Dasturchi;
import com.example.dataservice.PayLoad.ApiResponse;
import com.example.dataservice.Repository.DasturchiRepository;
import com.example.dataservice.Service.DasturchiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Dasturchi")
public class DasturchiController {
    @Autowired
    DasturchiService dasturchiService;
    @PostMapping("/dasturchiJoylash")
    public HttpEntity<?> DasturchiJoylash(@RequestBody Dasturchi dasturchi){
        ApiResponse apiResponse=dasturchiService.addDasturchi(dasturchi);
        return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.ALREADY_REPORTED).body(apiResponse.getXabar());
    }
    @PutMapping("/dasturchiTahrirlash/{id}")
    public HttpEntity<?> DasturchiTahrirlash(@PathVariable Integer id, @RequestBody Dasturchi dasturchi){
        ApiResponse apiResponse=dasturchiService.editDasturchi(id,dasturchi);
        return ResponseEntity.status((apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND)).body(apiResponse.getXabar());
    }
    @GetMapping("/dasturchiOqishIdlab/{id}")
    public HttpEntity<?> DasturchiOqish(@PathVariable Integer id){
        ApiResponse apiResponse=dasturchiService.idreadDasturchi(id);
        return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @GetMapping("/dasturchiOqish")
    public HttpEntity<?> DasturchiOqishIdlab(){
        ApiResponse apiResponse=dasturchiService.readDasturchi();
        return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
    @DeleteMapping("/dasturchiOchirish/{id}")
    public HttpEntity<?> DasturchiOchirish(@PathVariable Integer id){
        ApiResponse apiResponse=dasturchiService.delDasturchi(id);
        return ResponseEntity.status(apiResponse.isHolat() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(apiResponse.getXabar());
    }
}
