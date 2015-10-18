package io.swagger.sample.helpers;

import io.swagger.sample.models.Pet;

public class PetHelper {
	
	public static Pet getPetById(int id){
		Pet pet=new Pet();
		pet.setAge(9);
		pet.setHostFeature("3");
		pet.setId(id);
		pet.setName("fluffy");
		pet.setPetRequirement("xyz");
	    return pet;
		
	}

}
