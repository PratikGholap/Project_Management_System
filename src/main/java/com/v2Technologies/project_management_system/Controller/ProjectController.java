package com.v2Technologies.project_management_system.Controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.v2Technologies.project_management_system.DTO.ProjectDTO;
import com.v2Technologies.project_management_system.Service.ProjectService;
import com.v2Technologies.project_management_system.entity.Project;

@Controller
@RequestMapping("project")
@SessionAttributes("employee")
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	
	@GetMapping("project")
	public String showProjectScreen(Model m)
	{
		Project p=new Project();
		m.addAttribute("project", p);
		return "project/addProject";
	}
	
	@PostMapping("/add")
	public String saveProject(@ModelAttribute ProjectDTO project,BindingResult bindingResult,Model m,HttpServletRequest request)
	{
		Project project2=project.getCurrent();
		
		String startDate=request.getParameter("projectStartDate");
		if(startDate==null)
		{
			startDate=convertDateToString(project.getProjectEndDate());
		}
		project.setProjectStartDate(convertDate(startDate));
		project2.setProjectStartDate(project.getProjectStartDate());
	
		String endDate=request.getParameter("projectEndDate");
		if(endDate==null)
		{
			endDate=convertDateToString(project.getProjectEndDate());
		}
		project.setProjectEndDate(convertDate(endDate));
		project2.setProjectEndDate(project.getProjectEndDate());
		
		project2.setProjectName(project.getProjectName());
		project2.setPriority(project.getPriority());
		project2.setEmployee(project.getEmployee());
		
		System.out.println(project2);
		
		projectService.addproject(project2);
		return "redirect:/project/projects";
	}
	
	@GetMapping("projects")
	public String showAllProjects(Model m)
	{
		Iterable<Project> pl=projectService.findAll();
		m.addAttribute("projects", pl);
		return "project/allProjects";
	}

	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return (List<Project>)projectService.findAll();
	}
	
	@PostMapping("/search")
	public String searchProjectByName(@RequestParam("searchName") String projectName,Model m)
	{
		List<Project> li=projectService.findByProjectName(projectName);
		m.addAttribute("projects", li);
		return "project/allProjects";
	}
	
	@RequestMapping(value="sortOnStartDate", method=RequestMethod.GET)
	public String projectSortOnStartDate(Model m)
	{
		List<Project> projectList=projectService.findAllByOrderByProjectStartDateAsc();
		m.addAttribute("projects",projectList);
		return "project/allProjects";
	}
	
	@RequestMapping(value="sortOnLastDate", method=RequestMethod.GET)
	public String projectSortOnEndDate(Model m)
	{
		List<Project> projectList=projectService.findAllByOrderByProjectEndDateAsc();
		//Collections.sort(projectList,new projectSortOnEndDate());
		m.addAttribute("projects", projectList);
		return "project/allProjects";
	}
	
	@RequestMapping(value="priority" ,method=RequestMethod.GET)
	public String projectSortOnPriority(Model m)
	{
		List<Project> projects=projectService.findAllByOrderByPriorityAsc();
		m.addAttribute("projects",projects);
		return "project/allProjects";
	}
	
	private Date convertDate(String date) {
		try {
			String format = "yyyy-MM-dd";
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	private String convertDateToString(Date date) {
		String format = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
		//opbalRepo.
	}
	

}
