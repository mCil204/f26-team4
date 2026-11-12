<#import "layout.ftlh" as l>
<@l.page title="Something went wrong">
<div class="container py-5 text-center">
    <h1 class="h3 fw-bold">Something went wrong</h1>
    <p>${errorMessage!"We couldn't find that page."}</p>
    <a class="btn btn-primary" href="/customers/new">Back</a>
</div>
</@l.page>