<#import "layout.ftlh" as l>
<@l.page title="Home" active="home">
<section class="py-5">
  <div class="container text-center py-5">
    <h1 class="display-4 fw-bold">Cleaning and Sweeping Homes!</h1>
    <p class="lead text-body-secondary mx-auto" style="max-width: 800px;">
      Reliable, affordable, and professional cleaning services for your home or office.
    </p>

    <h2 class="h4 mt-5 mb-3">Get Started!</h2>
    <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
        <#if signedIn!false>
            <a class="btn btn-primary btn-lg px-4" href="/profile">Go to My Profile</a>
            <#else>
                <a class="btn btn-primary btn-lg px-4" href="/customers/new">I'm a Customer.</a>
                <a class="btn btn-outline-dark btn-lg px-4" href="/cleaners/notRealYet">I'm a Cleaner.</a>
        </#if>
    </div>
  </div>
</section>
</@l.page>