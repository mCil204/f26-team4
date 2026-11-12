<#import "layout.ftlh" as l>
<#assign days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]>
<#assign specialtyLabels = {
  "petFriendly": "Pet-friendly",
  "ecoFriendly": "Eco-friendly",
  "deepCleaning": "Deep cleaning",
  "commercialSpaces": "Commercial spaces"
}>

<@l.page title="Browse Cleaners" active="browse">
<section class="py-5 bg-body-tertiary">
  <div class="container">
    <h1 class="display-6 fw-bold mb-4">Find the Right Cleaner for You</h1>

    <div class="row g-4">
      <aside class="col-lg-3">
        <div class="card">
          <div class="p-4">
            <h2 class="h5 fw-bold mb-4">Filter Results</h2>
            <form method="get" action="/browse">
              <div class="mb-3">
                <label for="location" class="form-label">City or zip code</label>
                <input type="text" class="form-control" id="location" name="location" value="${location!}">
              </div>

              <div class="mb-3">
                <label for="maxRate" class="form-label">Max price per hour ($)</label>
                <input type="number" class="form-control" id="maxRate" name="maxRate" min="0" step="1"
                       value="<#if maxRate??>${maxRate?c}</#if>">
              </div>

              <div class="mb-3">
                <label for="availability" class="form-label">Available on</label>
                <select class="form-select" id="availability" name="availability">
                  <option value="">Any day</option>
                  <#list days as d>
                    <option value="${d}" <#if availability == d>selected</#if>>${d}</option>
                  </#list>
                </select>
              </div>

              <div class="mb-4">
                  <label for="specialty" class="form-label">Specialty</label>
                  <select class="form-select" id="specialty" name="specialty">
                      <option value="">Any</option>
                      <#list specialtyLabels?keys as k>
                          <option value="${k}" <#if specialty==k>selected</#if>>${specialtyLabels[k]}</option>
                      </#list>
                  </select>
              </div>

              <button type="submit" class="btn btn-primary w-100">Apply Filters</button>
              <a href="/browse" class="d-block text-center mt-2">Clear filters</a>
            </form>
          </div>
        </div>
      </aside>

      <div class="col-lg-9">
        <p class="text-body-secondary">${cleaners?size} cleaner(s) found</p>
        <div class="vstack gap-4">
          <#list cleaners as c>
            <article class="card">
              <div class="p-4">
                <h3 class="h5 fw-bold">${c.businessName!''}</h3>
                <p class="mb-1">${c.city!''}<#if c.state?has_content>, ${c.state}</#if></p>
                <p class="mb-1">&#36;${c.hourlyRate}/hr</p>
                <#if c.specialties??>
                  <p class="mb-1">Specialty: ${specialtyLabels[c.specialties?string]!c.specialties?string}</p>
                </#if>
                <#if c.availability??>
                  <p class="mb-1">Available: ${c.availability?string}</p>
                </#if>
                <#if c.businessDescription?has_content>
                  <p class="mb-0 text-body-secondary">${c.businessDescription?truncate(140, '...')}</p>
                </#if>
              </div>
            </article>
          <#else>
            <div class="card">
              <div class="p-4">No cleaners found. Try changing or clearing your filters.</div>
            </div>
          </#list>
        </div>
      </div>
    </div>
  </div>
</section>
</@l.page>