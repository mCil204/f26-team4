<#import "layout.ftlh" as l>
<#macro fieldError name>
    <#if errors[name]??>
        <div class="text-danger small mt-1">${errors[name]}</div>
    </#if>
</#macro>

<@l.page title=formTitle active="profile">
<div class="container py-5">
  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-6">
      <div class="card p-4 p-md-5">
        <h1 class="h3 fw-bold mb-4">${formTitle}</h1>

        <form method="post" action="${formAction}">
          <div class="row g-3 mb-3">
            <div class="col-md-6">
              <label for="firstName" class="form-label">First name</label>
              <input type="text" class="form-control" id="firstName" name="firstName" value="${form.firstName!}">
              <@fieldError "firstName"/>
            </div>
            <div class="col-md-6">
              <label for="lastName" class="form-label">Last name</label>
              <input type="text" class="form-control" id="lastName" name="lastName" value="${form.lastName!}">
              <@fieldError "lastName"/>
            </div>
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">Email address</label>
            <input type="email" class="form-control" id="email" name="email" value="${form.email!}">
            <@fieldError "email"/>
          </div>

          <div class="mb-3">
            <label for="phone" class="form-label">Phone number</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="${form.phone!}">
          </div>

          <div class="mb-3">
            <label for="street" class="form-label">Street address</label>
            <input type="text" class="form-control" id="street" name="street" value="${form.street!}">
            <@fieldError "street"/>
          </div>

          <div class="row g-3 mb-3">
            <div class="col-md-5">
              <label for="city" class="form-label">City</label>
              <input type="text" class="form-control" id="city" name="city" value="${form.city!}">
              <@fieldError "city"/>
            </div>
            <div class="col-md-3">
              <label for="state" class="form-label">State</label>
              <input type="text" class="form-control" id="state" name="state" value="${form.state!}">
              <@fieldError "state"/>
            </div>
            <div class="col-md-4">
              <label for="zip" class="form-label">Zip code</label>
              <input type="text" class="form-control" id="zip" name="zip" value="${form.zip!}">
              <@fieldError "zip"/>
            </div>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label"><#if isEdit>New password<#else>Create password</#if></label>
            <input type="password" class="form-control" id="password" name="password">
            <#if isEdit><div class="form-text">Leave blank to keep your current password.</div></#if>
            <@fieldError "password"/>
          </div>

          <div class="mb-4">
            <label for="confirmPassword" class="form-label">Confirm password</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword">
            <@fieldError "confirmPassword"/>
          </div>

          <button type="submit" class="btn btn-primary btn-lg w-100"><#if isEdit>Save Changes<#else>Create Profile</#if></button>
        </form>

        <#if isEdit>
            <a href="/profile" class="d-block text-center mt-3">Cancel</a>
            <#else>
                <p class="mt-4 mb-0 text-center text-body-secondary">
                    Already have an account? <a href="/login">Log in here.</a>
                </p>
        </#if>
      </div>
    </div>
  </div>
</div>
</@l.page>