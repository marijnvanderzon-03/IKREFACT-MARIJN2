package AfkoAPI.Controller;

import AfkoAPI.DAO.BlacklistDao;
import AfkoAPI.services.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BlacklistController {
    @Autowired
    BlacklistDao Bdao;

    @Autowired
    BlacklistService Bservice;
//
//    @GetMapping("/blacklist")
//    public HTTPResponse getBlackList(@RequestParam(name="max_amount", defaultValue="") String amount) {
//        return dao.getBlacklist(amount);
//    }
//
//    @PostMapping("/blacklist")
//    public HTTPResponse addToBlackList(@RequestBody BlacklistEntry[] entries) {
//        return dao.addMultipleToBlacklist(entries);
//    }
//
//    @PutMapping("/blacklist")
//    public HTTPResponse editBlacklistEntry(@RequestBody BlacklistEntry[] entries) {
//        if (entries.length != 2)
//            return HTTPResponse.<BlacklistEntry[]>returnFailure("length of entries is not 2");
//        return dao.editBlacklistEntry(entries);
//    }
//
//    @DeleteMapping("/blacklist")
//    public HTTPResponse removeBlacklistEntries(@RequestBody BlacklistEntry[] entries) {
//        return dao.removeMultipleFromBlacklist(entries);
//    }

    @PostMapping("/blacklist")
    public boolean InBlacklist(@RequestBody String abbreviation) throws Exception {
       return Bservice.checkAbbrInBlacklist(abbreviation);
    }

}