import GenericService, * as api from "../services/GenericService"
import React, { useState, useEffect } from 'react';

function KatalogFilter({ filterCallback }) {
    var adresa_cat = "https://127.0.0.1:4430/api/kategorije"
    const [filter_data, setData] = useState(null);
    const [include_selection, setIncluded] = useState([])
    const [exclude_selection, setExcluded] = useState([])
    const [substr, setSubstr] = useState("")

    const handleTextChange = (event) => {
        setSubstr(event.target.value)
    }

    const handleConfirm = () => {
        var filter_selection = {
            "included":include_selection,
            "excluded":exclude_selection,
            "name":substr
        }
        filterCallback(filter_selection)
    }

    const handleIncludeChange = (event) => {
        const { value, checked } = event.target;

        if (checked) {
            setIncluded((include_selection) => [...include_selection, value]);
        } else {
            setIncluded((include_selection) => include_selection.filter((cat) => cat !== value));
        }
    }
    const handleExcludeChange = (event) => {
        const { value, checked } = event.target;
        if (checked) {
            setExcluded((exclude_selection) => [...exclude_selection, value]);
        } else {
            setExcluded((exclude_selection) => exclude_selection.filter((cat) => cat !== value));
        }
    }

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await GenericService.dobaviSve(adresa_cat);
                const result = await response.json();
                setData(result);
            } catch (error) {
                console.error("Error fetching data:", error);
            }
        };
        fetchData();
    }, []);
    if (!filter_data) {
        <div className="flex flex-row  h-full text-white">Loading Filter Element...</div>
    } else {
        var include_list = []
        var exclude_list = []

        include_list = filter_data.map((categ) => (
            <label key={categ.id+"include"} className="inline-flex items-center cursor-pointer">
                <input type="checkbox" 
                        value={categ.id} 
                        // checked={include_selection.includes(categ.id)} 
                        className="sr-only peer" 
                        onChange={handleIncludeChange}/>
                <span className="m-1 peer-checked:border-white bg-gray-900 peer-checked:bg-green-900">{categ.naziv}</span>
            </label>
        ))
        exclude_list = filter_data.map((categ) => (
            <label key={categ.id+"exclude"} className="inline-flex items-center cursor-pointer">
                <input type="checkbox" 
                        value={categ.id} 
                        // checked={include_selection.includes(categ.id)} 
                        className="sr-only peer" 
                        onChange={handleExcludeChange}/>
                <span className="m-1 peer-checked:border-white bg-gray-900 peer-checked:bg-red-900">{categ.naziv}</span>
            </label>
        ))
        return (
            <div className="flex flex-row  h-full text-white">
                <div className="w-1/3 px-2">
                    <p>Name:</p>
                    <input type="text" 
                        value={substr} 
                        className="w-full bg-gray-900"
                        onChange={handleTextChange}></input>
                    <button className="border-white border-2" onClick={handleConfirm}>Filter</button>
                </div>
                <div className="w-1/3 px-2 flex flex-col h-full">
                    <div className="">Include Categories:</div>
                    <div className="flex flex-wrap w-full overflow-auto border border-2 border-dashed border-blue-500 h-full">
                        {include_list}
                    </div>
                </div>
                <div className="w-1/3 px-2 flex flex-col">
                    <p>Exclude Categories:</p>
                    <div className=" flex flex-wrap w-full overflow-auto border border-2 border-dashed border-blue-500 h-full">
                        {exclude_list}
                    </div>
                </div>
            </div>
        )
    }
}

export default KatalogFilter