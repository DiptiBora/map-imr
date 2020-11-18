package Vehicle_IMR;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Vehicle, EntityModel<Vehicle>> {

	@Override
	public EntityModel<Vehicle> toModel(Vehicle employee) {

		return EntityModel.of(employee, //
				linkTo(methodOn(VehicleController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(VehicleController.class).all()).withRel("employees"));
		
	}
}
