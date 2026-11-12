<#import "layout.ftlh" as l>
<@l.page title="My Profile" active="profile">
<section class="py-5 bg-body-tertiary">
  <div class="container">
    <div class="mx-auto" style="max-width: 800px;">
      <div class="card-body p-4 p-md-5">
        <h1 class="h3 fw-bold mb-0">${customer.firstName} ${customer.lastName}</h1>
        <hr>
        <dl class="row gy-3 my-4">
          <dt class="col-sm-3">Email</dt>
          <dd class="col-sm-9 mb-0">${customer.email}</dd>

          <dt class="col-sm-3">Phone</dt>
          <dd class="col-sm-9 mb-0">${customer.phone!'Not provided'}</dd>

          <dt class="col-sm-3">Address</dt>
          <dd class="col-sm-9 mb-0">
            <#if customer.street?has_content || customer.city?has_content>
              <#if customer.street?has_content>${customer.street}<br></#if>
              ${customer.city!''}<#if customer.state?has_content>, ${customer.state}</#if> ${customer.zip!''}
            <#else>
              Not provided
            </#if>
          </dd>
        </dl>
        <a class="btn btn-outline-dark" href="/profile/edit">Edit Profile</a>
      </div>
    </div>
  </div>
</section>
</@l.page>