/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.rest.api.identity;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.identity.User;
import org.activiti.rest.api.ActivitiUtil;
import org.activiti.rest.api.SecuredResource;


/**
 * @author Frederik Heremans
 */
public class BaseUserResource extends SecuredResource {

  protected User getUserFromRequest() {
    String userId = getAttribute("userId");
    if (userId == null) {
      throw new ActivitiIllegalArgumentException("The userId cannot be null");
    }

    User user = ActivitiUtil.getIdentityService().createUserQuery().userId(userId).singleResult();

    if (user == null) {
      throw new ActivitiObjectNotFoundException("Could not find a user with id '" + userId + "'.", User.class);
    }
    return user;
  }
}
