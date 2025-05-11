function KatalogFilter(){
    return (
        <div className="flex flex-row  h-full text-white">
            <div className="w-1/3 px-2">
                <p>Name:</p>
                <input type="text" className="w-full bg-gray-900"></input>
            </div>
            <div className="w-1/3 px-2 flex flex-col h-full">
                <div className="">Include Categories:<button className="bg-gray-900">Clear</button></div>
                <div className="flex flex-wrap w-full overflow-auto border border-2 border-dashed border-blue-500 h-full">
                    {/* demo item <span className="m-1 bg-gray-900">cat1</span> */}
                    
                </div>
            </div>
            <div className="w-1/3 px-2 flex flex-col">
                <p>Exclude Categories:<button className="bg-gray-900">Clear</button></p>
                <div className=" flex flex-wrap w-full overflow-auto border border-2 border-dashed border-blue-500 h-full">
                    
                </div>
            </div>
        </div>
    )
}

export default KatalogFilter