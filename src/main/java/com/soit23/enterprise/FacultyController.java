package com.soit23.enterprise;


import com.soit23.enterprise.entity.Faculty;
import com.soit23.enterprise.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Faculties")
public class FacultyController {

    public FacultyService facultyService;

    public FacultyController (FacultyService theFacultyService){
        facultyService = theFacultyService;

    }


    //Mapping for List
    @GetMapping("/list")
    public String listFaculties(Model theModel){
        //Retrieve faculties from the database
        List<Faculty> theFaculties = facultyService.findAll();

        //Add faculties to the Spring Model
        theModel.addAttribute("faculties", theFaculties);
        return "list-faculties";

    }


    @GetMapping("/viewAddForm")
    public String viewAddForm(Model theModel){

        theModel.addAttribute("faculty", theFaculty);
        return "faculties/faculty-form";
    }

    @PostMapping("/save")
    public String saveFaculty(@ModelAttribute("faculty") Faculty theFaculty){

        //Register the Faculty
        facultyService.save(theFaculty);

        //Block duplicate submission for accidental
        return "redirect:/Faculties/list";
    }


}
