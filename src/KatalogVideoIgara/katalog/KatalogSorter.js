function KatalogSorter(){
    return (
        <div className="flex flex-row w-full">
            <div className="flex flex-col w-1/2 m-1">
                <div>Sort By:</div>
                <div> <select name="sort-param" id="s-par" className="w-full"></select></div>
            </div>
            <div className="flex flex-col w-1/2 m-1">
                <div> <select name="asc-desc" id="s-acdc" className="w-full"></select></div>
                <div> <label>Compact <input type="checkbox" id="compact" name="compact"></input></label>
                </div>
            </div>
        </div>
    )
}

export default KatalogSorter