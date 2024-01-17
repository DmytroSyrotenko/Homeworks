package com.example.hw13.controller;

import com.example.hw13.dto.request.GroupRequestDto;
import com.example.hw13.dto.response.GroupResponseDto;
import com.example.hw13.dto.response.StudentResponseDto;
import com.example.hw13.facade.GroupFacade;
import com.example.hw13.facade.StudentFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping(path = "/groups")
@AllArgsConstructor
public class GroupController {

    private final GroupFacade groupFacade;
    private final StudentFacade studentFacade;
    @GetMapping
    public String allGroups(Model model) {
        model.addAttribute("groups", groupFacade.findAll());
        return "/pages/groups/groups_all";
    }

    @GetMapping("/update/{id}")
    public String redirectToUpdateGroup(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        model.addAttribute("group", groupResponseDto);
        return "pages/groups/group_update";
    }

    @PostMapping("/update/{id}")
    public String updateGroup(@PathVariable Long id, @ModelAttribute GroupRequestDto groupRequestDto) {
        groupFacade.update(groupRequestDto, id);
        return "redirect:/groups";
    }

    @GetMapping("/attach/{id}")
    public String redirectToAttachStudent(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        Collection<StudentResponseDto> collection = studentFacade.findAll();
        model.addAttribute("group", groupResponseDto);
        model.addAttribute("students", collection);
        return "pages/groups/groups_attach";
    }

    @PostMapping("/attach/{id}")
    public String attachStudent(@PathVariable Long id, @ModelAttribute GroupRequestDto groupRequestDto) {
        groupFacade.attachStudentToGroup(groupRequestDto, id);
        return "redirect:/groups";
    }

    @PostMapping("/remove/{id}")
    public String removeStudent(@PathVariable Long id, Model model,GroupResponseDto groupResponseDto) {
        System.out.println(groupResponseDto);
        return "redirect:/groups";
    }

    @GetMapping("/info/{id}")
    public String groupInfo(@PathVariable Long id, Model model) {
        GroupResponseDto groupResponseDto = groupFacade.findById(id);
        model.addAttribute("group", groupResponseDto);
        return "pages/groups/groups_info";
    }

    @GetMapping("/new")
    public String redirectToCreateGroup(Model model) {
        model.addAttribute("group", new GroupRequestDto());
        return "pages/groups/group_new";
    }

    @PostMapping("/new")
    public String createGroup(@ModelAttribute GroupRequestDto groupRequestDto) {
        groupFacade.create(groupRequestDto);
        return "redirect:/groups";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/groups";
    }


}
