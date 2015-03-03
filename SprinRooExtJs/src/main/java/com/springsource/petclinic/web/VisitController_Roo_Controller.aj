// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.petclinic.web;

import com.springsource.petclinic.domain.Pet;
import com.springsource.petclinic.domain.Vet;
import com.springsource.petclinic.domain.Visit;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect VisitController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String VisitController.create(@Valid Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("visit", visit);
            addDateTimeFormatPatterns(model);
            return "visits/create";
        }
        visit.persist();
        return "redirect:/visits/" + encodeUrlPathSegment(visit.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String VisitController.createForm(Model model) {
        model.addAttribute("visit", new Visit());
        addDateTimeFormatPatterns(model);
        List dependencies = new ArrayList();
        if (Pet.countPets() == 0) {
            dependencies.add(new String[]{"pet", "pets"});
        }
        model.addAttribute("dependencies", dependencies);
        return "visits/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String VisitController.show(@PathVariable("id") Long id, Model model) {
        addDateTimeFormatPatterns(model);
        model.addAttribute("visit", Visit.findVisit(id));
        model.addAttribute("itemId", id);
        return "visits/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String VisitController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("visits", Visit.findVisitEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Visit.countVisits() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("visits", Visit.findAllVisits());
        }
        addDateTimeFormatPatterns(model);
        return "visits/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String VisitController.update(@Valid Visit visit, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("visit", visit);
            addDateTimeFormatPatterns(model);
            return "visits/update";
        }
        visit.merge();
        return "redirect:/visits/" + encodeUrlPathSegment(visit.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String VisitController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("visit", Visit.findVisit(id));
        addDateTimeFormatPatterns(model);
        return "visits/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String VisitController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Visit.findVisit(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/visits?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    @RequestMapping(params = { "find=ByDescriptionAndVisitDate", "form" }, method = RequestMethod.GET)
    public String VisitController.findVisitsByDescriptionAndVisitDateForm(Model model) {
        addDateTimeFormatPatterns(model);
        return "visits/findVisitsByDescriptionAndVisitDate";
    }
    
    @RequestMapping(params = "find=ByDescriptionAndVisitDate", method = RequestMethod.GET)
    public String VisitController.findVisitsByDescriptionAndVisitDate(@RequestParam("description") String description, @RequestParam("visitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date visitDate, Model model) {
        model.addAttribute("visits", Visit.findVisitsByDescriptionAndVisitDate(description, visitDate).getResultList());
        addDateTimeFormatPatterns(model);
        return "visits/list";
    }
    
    @RequestMapping(params = { "find=ByVisitDateBetween", "form" }, method = RequestMethod.GET)
    public String VisitController.findVisitsByVisitDateBetweenForm(Model model) {
        addDateTimeFormatPatterns(model);
        return "visits/findVisitsByVisitDateBetween";
    }
    
    @RequestMapping(params = "find=ByVisitDateBetween", method = RequestMethod.GET)
    public String VisitController.findVisitsByVisitDateBetween(@RequestParam("minVisitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date minVisitDate, @RequestParam("maxVisitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date maxVisitDate, Model model) {
        model.addAttribute("visits", Visit.findVisitsByVisitDateBetween(minVisitDate, maxVisitDate).getResultList());
        addDateTimeFormatPatterns(model);
        return "visits/list";
    }
    
    @RequestMapping(params = { "find=ByDescriptionLike", "form" }, method = RequestMethod.GET)
    public String VisitController.findVisitsByDescriptionLikeForm(Model model) {
        return "visits/findVisitsByDescriptionLike";
    }
    
    @RequestMapping(params = "find=ByDescriptionLike", method = RequestMethod.GET)
    public String VisitController.findVisitsByDescriptionLike(@RequestParam("description") String description, Model model) {
        model.addAttribute("visits", Visit.findVisitsByDescriptionLike(description).getResultList());
        return "visits/list";
    }
    
    @ModelAttribute("pets")
    public Collection<Pet> VisitController.populatePets() {
        return Pet.findAllPets();
    }
    
    @ModelAttribute("vets")
    public Collection<Vet> VisitController.populateVets() {
        return Vet.findAllVets();
    }
    
    void VisitController.addDateTimeFormatPatterns(Model model) {
        model.addAttribute("visit_maxvisitdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        model.addAttribute("visit_minvisitdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
        model.addAttribute("visit_visitdate_date_format", DateTimeFormat.patternForStyle("S-", LocaleContextHolder.getLocale()));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object VisitController.showJson(@PathVariable("id") Long id) {
        Visit visit = Visit.findVisit(id);
        if (visit == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        return visit.toJson();
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> VisitController.createFromJsonArray(@RequestBody String json) {
        for (Visit visit: Visit.fromJsonArrayToVisits(json)) {
            visit.persist();
        }
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> VisitController.updateFromJsonArray(@RequestBody String json) {
        for (Visit visit: Visit.fromJsonArrayToVisits(json)) {
            if (visit.merge() == null) {
                return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByDescriptionAndVisitDate", method = RequestMethod.GET, headers = "Accept=application/json")
    public String VisitController.jsonFindVisitsByDescriptionAndVisitDate(@RequestParam("description") String description, @RequestParam("visitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date visitDate, Model model) {
        return Visit.toJsonArray(Visit.findVisitsByDescriptionAndVisitDate(description, visitDate).getResultList());
    }
    
    @RequestMapping(params = "find=ByVisitDateBetween", method = RequestMethod.GET, headers = "Accept=application/json")
    public String VisitController.jsonFindVisitsByVisitDateBetween(@RequestParam("minVisitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date minVisitDate, @RequestParam("maxVisitDate") @org.springframework.format.annotation.DateTimeFormat(style = "S-") Date maxVisitDate, Model model) {
        return Visit.toJsonArray(Visit.findVisitsByVisitDateBetween(minVisitDate, maxVisitDate).getResultList());
    }
    
    @RequestMapping(params = "find=ByDescriptionLike", method = RequestMethod.GET, headers = "Accept=application/json")
    public String VisitController.jsonFindVisitsByDescriptionLike(@RequestParam("description") String description, Model model) {
        return Visit.toJsonArray(Visit.findVisitsByDescriptionLike(description).getResultList());
    }
    
    String VisitController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
