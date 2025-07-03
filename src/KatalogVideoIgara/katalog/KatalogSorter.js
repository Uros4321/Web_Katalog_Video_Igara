import React, { useState, useEffect } from 'react';

function KatalogSorter({ sortCallback }) {
    const [parameter, setParam] = useState("name")
    const [order, serOrder] = useState("asc")
    const [compact, setCompact] = useState(false)

    const handleSort = () => {
        var sort_selection = {
            "parameter":parameter,
            "order":order,
            "compact":compact
        }
        sortCallback(sort_selection)
    }
    return (
        <div className="flex flex-row w-full text-white">
            <div className="flex flex-col w-1/2 m-1">
                <div>Sort By:</div>
                <div> <select name="sort-param" value={parameter} onChange={(event) => setParam(event.target.value)} id="s-par" className="w-full bg-gray-900 ">
                    <option value="name">Name</option>
                    <option value="date">Release Date</option>
                </select></div>
            </div>
            <div className="flex flex-col w-1/2 m-1">
                <div> <select name="asc-desc" value={order} onChange={(event) => serOrder(event.target.value)} id="s-acdc" className="w-full bg-gray-900">
                    <option value="asc">Ascending</option>
                    <option value="desc">Descending</option>
                </select></div>
                <div> <label>Compact <input type="checkbox" onChange={(event) => setCompact(event.target.checked)} id="compact" name="compact"></input></label>
                </div>
                <div ><button className='border-2 border-color-white' onClick={handleSort}>Sort</button></div>
            </div>
            
        </div>
        
    )
}

export default KatalogSorter