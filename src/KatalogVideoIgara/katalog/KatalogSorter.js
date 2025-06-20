function KatalogSorter() {
    return (
        <div className="flex flex-row w-full text-white">
            <div className="flex flex-col w-1/2 m-1">
                <div>Sort By:</div>
                <div> <select name="sort-param" id="s-par" className="w-full bg-gray-900 ">
                    <option value="name">Name</option>
                    <option value="date">Release Date</option>
                </select></div>
            </div>
            <div className="flex flex-col w-1/2 m-1">
                <div> <select name="asc-desc" id="s-acdc" className="w-full bg-gray-900">
                    <option value="asc">Ascending</option>
                    <option value="desc">Descending</option>
                </select></div>
                <div> <label>Compact <input type="checkbox" id="compact" name="compact"></input></label>
                </div>
            </div>
        </div>
    )
}

export default KatalogSorter