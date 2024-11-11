import "./katalog/KatalogIgara"
import KatalogIgara from "./katalog/KatalogIgara"

function KatalogMainPage(){
    return (
        <div className="bg-[url('https://asset.gecdesigns.com/img/background-templates/dark-blue-background-with-the-diamond-pattern-template-sr06012408-1704699724568-cover.webp')]">
            <nav className="bg-transparent border-gray-200 dark:bg-gray-900  m-0 p-0 h-10">
                <div className="w-2/3 h-full flex flex-wrap items-center justify-between mx-auto  m-0 p-0">
                    <button className="bg-gray-400 w-1/4 h-full border-4 border-r-2 border-gray-800">Home</button>
                    <button className="bg-gray-400 w-1/4 h-full border-4 border-l-2 border-r-2 border-gray-800">Katalog</button>
                    <button className="bg-gray-400 w-1/4 h-full border-4 border-l-2 border-r-2 border-gray-800">Account</button>
                    <button className="bg-gray-400 w-1/4 h-full border-4 border-l-2 border-gray-800">Log Out</button>
                </div>
            </nav>
            <div className="flex justify-center  h-screen">
                <KatalogIgara/>
            </div>
        </div>
    )
}

export default KatalogMainPage