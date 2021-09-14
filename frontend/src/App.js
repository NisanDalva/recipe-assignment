import React, { useEffect, useState } from "react";
import "./App.css";
import Recipe from "./components/Recipe";

const App = () => {
  const [allRecipes, setAllRecipes] = useState([]); // save the recipes as a list
  const [query, setQuery] = useState(""); // will be update in real time when user is writing
  const [checkedFavoritesValue, setCheckedFavoritesValue] = useState(false); // if true - showing the favorite list

  const [cuisine, setCuisine] = useState("");
  const [diet, setDiet] = useState("");
  const [type, setType] = useState("");

  useEffect(() => {
    getListOfRecipes(query, cuisine, diet, type);
    // to disable an warning
    // eslint-disable-next-line
  }, []);

  const getListOfRecipes = async (query, cuisine, diet, type) => {
    const response = await fetch(
      `/recipes/search?q=${query}&cuisine=${cuisine}&diet=${diet}&type=${type}`,
      {
        mode: "no-cors",
      }
    );
    const data = await response.json();
    setAllRecipes(data);
  };

  const updateSearch = (e) => {
    setQuery(e.target.value);
  };

  const searchQuery = (e) => {
    e.preventDefault();
    setQuery(query);
    getListOfRecipes(query, diet, cuisine, type);
    setQuery("");
  };

  const handleFavorites = () => {
    setCheckedFavoritesValue(!checkedFavoritesValue);

    if (!checkedFavoritesValue) {
      getFavoritesRecipes();
    } else {
      getListOfRecipes("", "", "", "");
    }
  };

  const getFavoritesRecipes = async () => {
    const response = await fetch("/recipes/allFavorites", { mode: "no-cors" });
    const data = await response.json();
    setAllRecipes(data);
  };

  const handleDiets = (e) => {
    setDiet(e.target.value);
  };

  const handleCuisines = (e) => {
    setCuisine(e.target.value);
  };

  const handleTypes = (e) => {
    setType(e.target.value);
  };

  return (
    <div className="App">
      <form onSubmit={searchQuery} className="search-form">
        <input
          placeholder="Search for a recipe"
          className="search-bar"
          type="text"
          value={query}
          onChange={updateSearch}
        />
        <button className="search-button" type="submit">
          Search
        </button>
        <label>
          <input
            type="checkbox"
            onChange={handleFavorites}
            checked={checkedFavoritesValue}
          />
          Show Favorites
        </label>
        <div className="cuisines-select">
          <select onChange={handleCuisines}>
            <option value="">Select Cuisine</option>
            <option value="African">African</option>
            <option value="American">American</option>
            <option value="British">British</option>
            <option value="Chinese">Chinese</option>
            <option value="European">European</option>
            <option value="French">French</option>
            <option value="Indian">Indian</option>
            <option value="Italian">Italian</option>
            <option value="Jewish">Jewish</option>
            <option value="Thai">Thai</option>
          </select>
        </div>
        <div className="diets-select">
          <select onChange={handleDiets}>
            <option value="">Select Diet</option>
            <option value="Gluten Free">Gluten Free</option>
            <option value="Ketogenic">Ketogenic</option>
            <option value="Vegetarian">Vegetarian</option>
            <option value="Lacto-Vegetarian">Lacto-Vegetarian</option>
            <option value="Ovo-Vegetarian">Ovo-Vegetarian</option>
            <option value="Vegan">Vegan</option>
            <option value="Pescetarian">Pescetarian</option>
            <option value="Paleo">Paleo</option>
            <option value="Primal">Primal</option>
            <option value="Whole30">Whole30</option>
          </select>
        </div>
        <div className="types-select">
          <select onChange={handleTypes}>
            <option value="">Select Type</option>
            <option value="side dish">side dish</option>
            <option value="dessert">dessert</option>
            <option value="appetizer">appetizer</option>
            <option value="salad">salad</option>
            <option value="bread">bread</option>
            <option value="breakfast">breakfast</option>
            <option value="soup">soup</option>
            <option value="sauce">sauce</option>
            <option value="snack">snack</option>
            <option value="drink">drink</option>
          </select>
        </div>
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
      </div>
    </div>
  );
};

export default App;
