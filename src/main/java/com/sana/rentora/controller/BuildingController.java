package com.sana.rentora.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sana.rentora.entity.Building;
import com.sana.rentora.service.BuildingService;

@Controller
public class BuildingController {

		private static final Logger logger = LoggerFactory.getLogger(BuildingController.class);
		
		private final BuildingService buildServ;
		
		public BuildingController(BuildingService buildServ) {
			this.buildServ = buildServ;
		}
		
		@GetMapping("buildings/new")
		public String showAddBuildingForm(Model model) {
			logger.info("Opening Add Building Form");
			model.addAttribute("building",new Building());
			return "building_form";
		}
		
		
		@PostMapping("buildings/save")
		public String savebuilding(Building building) {
			buildServ.saveBuilding(building);
			return "redirect:/buildings";
		}
		
		@GetMapping("/buildings")
		public String listBuildings(Model model) {
			model.addAttribute("buildings", buildServ.getAllBuildings());
			return "building_list";
		}
		
		@GetMapping("/buildings/edit/{id}")
		public String showEditForm(@PathVariable Long id,Model model) {
			Building building = buildServ.getBuildingById(id);
			model.addAttribute("building",building);
			return "building_form";
		}
		
		@GetMapping("/buildings/delete/{id}")
		public String deleteBuilding(@PathVariable Long id) {
			buildServ.deleteBuilding(id);
			return "redirect:/buildings";
		}

		
}
