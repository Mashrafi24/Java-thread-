# ============================================================
# Show Test Cases
#
# Run TC4 only:
# .\show_cases.ps1 4
#
# Run all test cases:
# .\show_cases.ps1
# ============================================================

param([int]$TC = 0)

$CLASS = "Mashrafi_Thread"


# ============================================================
# Compile Java program
# ============================================================

javac "$CLASS.java"

if ($LASTEXITCODE -ne 0) {

    Write-Host "Compile failed"
    exit 1
}


# ============================================================
# Test Cases
#
# Format:
# TC Number, Threads, Increments per Thread
# ============================================================

$cases = @(
    @(1, 1, 1000),
    @(2, 2, 10000),
    @(3, 5, 10000),
    @(4, 10, 50000),
    @(5, 20, 50000),
    @(6, 50, 50000),
    @(7, 100, 50000)
)


# ============================================================
# Run selected test case(s)
# ============================================================

foreach ($c in $cases) {

    # If a specific TC is provided,
    # skip all other test cases.
    if ($TC -ne 0 -and $c[0] -ne $TC) {
        continue
    }


    # ========================================================
    # SAFE and UNSAFE modes
    # ========================================================

    foreach ($mode in "true", "false") {

        Write-Host ""

        Write-Host "================================================"

        Write-Host "TC$($c[0])"

        Write-Host "Command:"
        
        Write-Host "java $CLASS $($c[1]) $($c[2]) $mode"

        Write-Host "================================================"


        # Run Java program

        java $CLASS $c[1] $c[2] $mode
    }
}