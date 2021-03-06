package com.v2Technologies.project_management_system.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.v2Technologies.project_management_system.Repository.ProjectRepository;
import com.v2Technologies.project_management_system.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService
{
	@Autowired
	private ProjectRepository projectRepo;

	@Override
	public void addproject(Project project)
	{
		projectRepo.save(project);
	}

	@Override
	public List<Project> findByProjectName(String projectName) {
		// TODO Auto-generated method stub
		return projectRepo.findByProjectName(projectName);
	}

	@Override
	public List<Project> findAll() {
		// TODO Auto-generated method stub
		return projectRepo.findAll();
	}

	@Override
	public List<Project> findAllByOrderByPriorityAsc() {
		// TODO Auto-generated method stub
		return projectRepo.findAllByOrderByPriorityAsc();
	}

	@Override
	public List<Project> findAllByOrderByProjectStartDateAsc() {
		// TODO Auto-generated method stub
		return projectRepo.findAllByOrderByProjectStartDateAsc();
	}

	@Override
	public List<Project> findAllByOrderByProjectEndDateAsc() {
		// TODO Auto-generated method stub
		return projectRepo.findAllByOrderByProjectEndDateAsc();
	}

}
