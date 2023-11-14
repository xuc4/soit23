package com.soit23.enterprise;


import com.soit23.enterprise.entity.Faculty;
import com.soit23.enterprise.service.FacultyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "faculties/list-faculties";

    }


    @GetMapping("/viewAddForm")
    public String viewAddForm(Model theModel){

        //Model attribute for the data binding
        Faculty theFaculty = new Faculty();

        theModel.addAttribute("faculty", theFaculty);
        return "faculties/faculty-form";
    }
    
    @GetMapping("/viewUpdateForm")
    public String viewUpdateForm(@RequestParam("facultyId") int theId, Model theModel) {
        
        //Retrieve the faculty info from the service layer
        Faculty theFaculty = facultyService.findById(theId);
        
        //Pre-populate the form by setting the faculty as a model attribute
        theModel.addAttribute("faculty", theFaculty);

        //Redirect us to the faculty form
        return "faculties/faculty-form";
        
    }

    @PostMapping("/save")
    public String saveFaculty(@ModelAttribute("faculty") Faculty theFaculty){

        //Register the Faculty
        facultyService.save(theFaculty);

        //Block duplicate submission for accidental
        return "redirect:/Faculties/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("facultyId") int theId) {

        //Delete Faculty
        facultyService.deleteById(theId);

        //Return to the Faculty's Directory
        return "redirect:/Faculties/list";

    }


}
