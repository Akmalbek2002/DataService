package com.example.dataservice.Service;

import com.example.dataservice.Entity.Dasturchi;
import com.example.dataservice.PayLoad.ApiResponse;
import com.example.dataservice.Repository.DasturchiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DasturchiService {
    @Autowired
    DasturchiRepository dasturchiRepository;

    public ApiResponse addDasturchi(Dasturchi dasturchi) {
        boolean b = dasturchiRepository.existsByEmail(dasturchi.getEmail());
        if (b){
            return new ApiResponse("Bunday dasturchi avval ro'yxatdan o'tgan", false);
        }
        dasturchiRepository.save(dasturchi);
        return new ApiResponse("Dasturchi bazaga muvoffaqiyatli saqlandi", true);
    }

    public ApiResponse editDasturchi(Integer id, Dasturchi dasturchi) {
        Optional<Dasturchi> byId = dasturchiRepository.findById(id);
        if (byId.isPresent()){
            Dasturchi dasturchi1=byId.get();
            dasturchi1.setIsm(dasturchi.getIsm());
            dasturchi1.setFamilya(dasturchi.getFamilya());
            dasturchi1.setYosh(dasturchi.getYosh());
            dasturchi1.setMaosh(dasturchi.getMaosh());
            dasturchi1.setLavozim(dasturchi.getLavozim());
            dasturchiRepository.save(dasturchi1);
            return new ApiResponse("Ma'lumotlar tahrirlandi", true);
        }
        return new ApiResponse("Bazada bunday idli xodim mavjud emas", false);
    }
    //Dasturchini idlab o'qish
    public ApiResponse idreadDasturchi(Integer id) {
        Optional<Dasturchi> byId = dasturchiRepository.findById(id);
        if (byId.isPresent()){
            List<Dasturchi> list=dasturchiRepository.findAll();
            for (Dasturchi dasturchi : list) {
                String[] matn=dasturchi.toString().split(", ");
                String ss="";
                for (String s : matn) {
                    if (s.indexOf("(")>0){
                        s=s.substring(s.indexOf("(")+1);
                    }
                    if (s.indexOf(")")>0){
                        s=s.substring(0,s.indexOf(")"));
                    }
                    ss+=s+"\n";
                }
                if (dasturchi.getId()==id) return new ApiResponse(ss,true);
            }
        }
        return new ApiResponse("Bazada bunday idli dasturchi mavjud emas!",false);
    }

    public ApiResponse readDasturchi() {
        List<Dasturchi> list=dasturchiRepository.findAll();
        String ss="";
        for (Dasturchi dasturchi : list) {
            String[] matn=dasturchi.toString().split(", ");
            for (String s : matn) {
                if (s.indexOf("(")>0){
                    s=s.substring(s.indexOf("(")+1);
                }
                if (s.indexOf(")")>0){
                    s=s.substring(0,s.indexOf(")"));
                }
                ss+=s+"\n";
            }
            ss+="\n";
        }
        return new ApiResponse(ss,true);
    }

    public ApiResponse delDasturchi(Integer id) {
        Optional<Dasturchi> byId = dasturchiRepository.findById(id);
        if (byId.isPresent()){
            dasturchiRepository.deleteById(id);
            return new ApiResponse("Muvoffaqiyatli o'chirildi", true);
        }
        return new ApiResponse("Bazada buday idli hodim mavjud emas!!!", false);
    }
}
