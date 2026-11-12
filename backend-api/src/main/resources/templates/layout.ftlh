<#macro page title active="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title} | CleanSweep</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/style.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" defer></script>
</head>
<body class="d-flex flex-column min-vh-100">
    <header>
        <nav class="navbar navbar-expand site-nav" data-bs-theme="dark">
            <div class="container">
                <a class="navbar-brand fw-bold" href="/">CleanSweep</a>
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link ${(active == 'home')?then('active', '')}" href="/">Home</a></li>
                    <#if signedIn!false>
                        <li class="nav-item"><a class="nav-link ${(active == 'browse')?then('active', '')}" href="/browse">Browse</a></li>
                        <li class="nav-item"><a class="nav-link ${(active == 'profile')?then('active', '')}" href="/profile">Profile</a></li>
                    </#if>
                </ul>
                <#if signedIn!false>
                    <form method="post" action="/logout" class="d-inline">
                        <button type="submit" class="btn btn-outline-primary">Log Out</button>
                    </form>
                <#else>
                    <div class="d-flex gap-2">
                        <a class="btn btn-primary" href="/login">Login</a>
                        <a class="btn btn-outline-primary" href="/customers/new">Create Profile</a>
                    </div>
                </#if>
            </div>
        </nav>
    </header>

    <main class="flex-grow-1">
        <div class="container pt-4">
            <#if message??>
                <div class="alert alert-success">${message}</div>
            </#if>
            <#if error??>
                <div class="alert alert-danger">${error}</div>
            </#if>
        </div>
        <#nested>
    </main>

    <footer class="site-footer py-5">
        <p class="mb-0 small text-center">
            &copy; 2026 CleanSweep. All rights reserved. For Educational Purposes Only.
        </p>
    </footer>
</body>
</html>
</#macro>