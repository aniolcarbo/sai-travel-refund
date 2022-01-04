title Running three approval applications

start "Running Software approval..." cmd /c gradlew :travel-approval:run --args "administrationRequestQueue InternshipAdministration"
start "Running Technology approval..." cmd /c gradlew :travel-approval:run --args "financeRequestQueue FinancialDepartment"

exit