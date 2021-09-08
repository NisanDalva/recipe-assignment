import React, { useEffect, useState } from "react";
import "./App.css";
import Recipe from "./Recipe";

const App = () => {
  const [allRecipes, setAllRecipes] = useState([]); // save the recipes as a list
  const [tmpSearch, setTmpSearch] = useState(""); // will be update in real time when user is writing
  const [finalSearch, setFinalSearch] = useState(""); // will be update only when user clicks on submit
  const [checkedFavoritesValue, setCheckedFavoritesValue] = useState(false); // if true - showing the favorite list

  useEffect(() => {
    // console.log(`test json = ${JSON.stringify({ id: finalSearch})}`)
    // console.log(`finalSearch = "${finalSearch}"`)
    getListOfRecipes(finalSearch);
    // console.log("efected");
  }, [finalSearch]);

  const getListOfRecipes = async (query) => {
    const response = await fetch(`/search?q=${query}`, { mode: "no-cors" }); // will be `/search/${query}`
    const data = await response.json();
    setAllRecipes(data);
    // console.log(data);
  };

  const updateSearch = (e) => {
    setTmpSearch(e.target.value);
    // console.log(e.target.value);
  };

  const searchQuery = (e) => {
    e.preventDefault();
    setFinalSearch(tmpSearch);
    setTmpSearch("");
  };

  const handleFavorites = () => {
    setCheckedFavoritesValue(!checkedFavoritesValue);

    if (!checkedFavoritesValue) {
      getFavoritesRecipes();
    } else {
      getListOfRecipes("");
    }
  };

  const getFavoritesRecipes = async () => {
    const response = await fetch("allFavorites", { mode: "no-cors" }); //allFavorites
    const data = await response.json();
    setAllRecipes(data);
    console.log(data);
  };

  // console.log(allRecipes);
  // console.log(`fffff ------- ${allRecipes[2]}`);

  return (
    <div className="App">
      <form onSubmit={searchQuery} className="search-form">
        <input
          placeholder="Search for a recipe"
          className="search-bar"
          type="text"
          value={tmpSearch}
          onChange={updateSearch}
        />
        <button className="search-button" type="submit">
          Search
        </button>
        <label>
          <input
            type="checkbox"
            onChange={handleFavorites}
            //   onClick={handleFavorites}
            checked={checkedFavoritesValue}
          />
          Show Favorites
        </label>
      </form>
      <div className="recipes">
        {allRecipes.map((r) => (
          <Recipe
            key={r.id}
            recipeId={r.id}
            title={r.title}
            summary={r.summary}
            analyzedInstructions={r.analyzedInstructions}
            image={r.image}
          />
        ))}
        ;
      </div>
    </div>
  );
};

export default App;
