package nosi.core.webapp.activit.rest;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;
import nosi.core.webapp.helpers.ResponseError;
import nosi.core.webapp.helpers.RestRequestHelper;

/**
 * @author: Emanuel Pereira
 * 27 Sep 2017
 */
public class UserService extends Activit{

	private String firstName;
	private String lastName;
	private String email;
	private String password;

	
	public UserService() {
	}

	public UserService getUser(String id){
		UserService d = new UserService();
		ClientResponse response = RestRequestHelper.get("identity/users",id);
		if(response!=null){
			String contentResp = response.getEntity(String.class);
			if(response.getStatus()==200){
				d = (UserService) RestRequestHelper.convertJsonToDao(contentResp, UserService.class);
			}else{
				d.setError((ResponseError) RestRequestHelper.convertJsonToDao(contentResp, ResponseError.class));
			}
		}
		return d;
	}


	@SuppressWarnings("unchecked")
	public List<UserService> getUsers(){
		List<UserService> d = new ArrayList<>();
		ClientResponse response = RestRequestHelper.get("identity/users");
		if(response!=null){
			String contentResp = response.getEntity(String.class);
			if(response.getStatus()==200){
				UserService dep = (UserService) RestRequestHelper.convertJsonToDao(contentResp, this.getClass());
				this.setTotal(dep.getTotal());
				this.setSize(dep.getSize());
				this.setSort(dep.getSort());
				this.setOrder(dep.getOrder());
				this.setStart(dep.getStart());
				d = (List<UserService>) RestRequestHelper.convertJsonToListDao(contentResp,"data", new TypeToken<List<UserService>>(){}.getType());
			}else{
				this.setError((ResponseError) RestRequestHelper.convertJsonToDao(contentResp, ResponseError.class));
			}
		}
		return d;
	}
	
	public UserService create(UserService user){
		UserService d = new UserService();
		ClientResponse response = RestRequestHelper.post("identity/users",RestRequestHelper.convertDaoToJson(user));
		if(response!=null){
			String contentResp = response.getEntity(String.class);
			if(response.getStatus()==201){
				d = (UserService) RestRequestHelper.convertJsonToDao(contentResp, UserService.class);
			}else{
				d.setError((ResponseError) RestRequestHelper.convertJsonToDao(contentResp, ResponseError.class));
			}
		}
		return d;
	}
	

	public UserService update(UserService user){
		UserService d = new UserService();
		ClientResponse response = RestRequestHelper.put("identity/users",RestRequestHelper.convertDaoToJson(user),user.getId());
		if(response!=null){
			String contentResp = response.getEntity(String.class);
			if(response.getStatus()==200){
				d = (UserService) RestRequestHelper.convertJsonToDao(contentResp, UserService.class);
			}else{
				d.setError((ResponseError) RestRequestHelper.convertJsonToDao(contentResp, ResponseError.class));
			}
		}
		return d;
	}
	
	public boolean delete(String id){
		ClientResponse response = RestRequestHelper.delete("identity/users",id);
		return response!=null && response.getStatus()==204;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
