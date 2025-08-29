// Auto-hide flash messages (success or error)
window.addEventListener("DOMContentLoaded", () => {
    const successMsg = document.querySelector("p[style*='color:green']");
    const errorMsg = document.querySelector("p[style*='color:red']");

    if (successMsg || errorMsg) {
        setTimeout(() => {
            if (successMsg) successMsg.style.display = "none";
            if (errorMsg) errorMsg.style.display = "none";
        }, 4000); // Hide after 4 seconds
    }

    // If on reports.jsp, restore filters
    const reportType = document.querySelector("select[name='type']");
    if (reportType) {
        toggleFilters(reportType.value);
        reportType.addEventListener("change", (e) => {
            toggleFilters(e.target.value);
        });
    }
});

// Toggle filters in reports.jsp
function toggleFilters(type) {
    const salesFilters = document.getElementById("salesFilters");
    const inventoryFilters = document.getElementById("inventoryFilters");

    if (salesFilters) salesFilters.style.display = (type === "sales") ? "block" : "none";
    if (inventoryFilters) inventoryFilters.style.display = (type === "inventory") ? "block" : "none";
}
