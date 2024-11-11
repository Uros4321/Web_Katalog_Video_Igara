function KatalogIgara(){
    return (
        <div className="flex flex-col w-2/3 bg-gray-700">
            <div className="h-1/6 w-full bg-green-700">
             <p>filter</p>
            </div>
            <ul className="h-full">
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game1 image</div><div> game1 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game2 image</div><div> game2 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game3 image</div><div> game3 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game4 image</div><div> game4 description</div></li>
                <li className="h-1/5 flex flex-row"><div className="w-1/5 bg-blue-600">game5 image</div><div> game5 description</div></li>
            </ul>
            <div className="h-1/6 w-full bg-green-700">
             <p>pagination</p>
            </div>
        </div>
    )
}

export default KatalogIgara