<#import "layout.ftlh" as l>
<@l.page title="Login">
<div class="container py-5">
  <div class="card mx-auto" style="max-width: 400px;">
    <div class="p-4 p-md-5">
      <h1 class="h3 fw-bold mb-4">Welcome Back</h1>

      <form method="post" action="/login">
        <div class="mb-3">
          <label for="email" class="form-label">Email address</label>
          <input type="email" class="form-control" id="email" name="email" value="${email!}" required>
        </div>

        <div class="mb-3">
          <label for="password" class="form-label">Password</label>
          <input type="password" class="form-control" id="password" name="password" required>
        </div>

        <button type="submit" class="btn btn-primary btn-lg w-100 mt-2">Log In</button>
      </form>

      <p class="mt-4 mb-0 text-center text-body-secondary">
        New here? <a href="/customers/new">Create a profile</a>
      </p>
    </div>
  </div>
</div>
</@l.page>