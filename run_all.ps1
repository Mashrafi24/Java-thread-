# Run in PowerShell:
# powershell -ExecutionPolicy Bypass -File .\run_all.ps1

$CLASS = "Mashrafi_Thread"

# Compile
javac "$CLASS.java"

if ($LASTEXITCODE -ne 0) {
    Write-Host "Compile failed"
    exit 1
}

# Create outputs folder
New-Item -ItemType Directory -Force -Path outputs | Out-Null

# Test cases
$inc = @{
    1   = 1000
    2   = 10000
    5   = 10000
    10  = 50000
    20  = 50000
    50  = 50000
    100 = 50000
}

# CSV file
$res = "outputs/results.csv"

"threads,increments,mode,run,expected,static,nonstatic,absdiff,pct" |
    Out-File $res -Encoding ascii


# Function to extract values from Java output
function Get-Val($text, $label) {

    $line = (
        $text |
        Select-String -SimpleMatch $label |
        Select-Object -First 1
    ).Line

    if ($null -eq $line) {
        return "ERROR"
    }

    return (($line -split ":")[1]).Trim()
}


# Run all test cases
foreach ($n in 1,2,5,10,20,50,100) {

    $m = $inc[$n]

    # Safe = 1 run
    # Unsafe = 5 runs
    $runs = @(
        @("true",1),
        @("false",1),
        @("false",2),
        @("false",3),
        @("false",4),
        @("false",5)
    )


    foreach ($r in $runs) {

        $mode = $r[0]
        $run = $r[1]

        Write-Host ""
        Write-Host "Threads=$n Increments=$m Mode=$mode Run=$run"


        # Run Java program
        $out = java $CLASS $n $m $mode


        # Save complete output
        $filename =
            "outputs/T${n}_${mode}_run${run}.txt"

        $out |
            Out-File $filename -Encoding ascii


        # Extract values
        $exp = Get-Val $out "Expected"
        $st  = Get-Val $out "Static Count"
        $ns  = Get-Val $out "Non-static Total"
        $ad  = Get-Val $out "Difference"
        $pc  = Get-Val $out "Difference (%)"


        # Save result to CSV
        "$n,$m,$mode,$run,$exp,$st,$ns,$ad,$pc" |
            Out-File $res -Append -Encoding ascii
    }
}


Write-Host ""
Write-Host "======================================"
Write-Host "All experiments completed!"
Write-Host "======================================"
Write-Host "Results: outputs/results.csv"