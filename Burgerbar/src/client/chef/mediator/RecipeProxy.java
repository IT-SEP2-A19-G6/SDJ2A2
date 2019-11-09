package client.chef.mediator;

public class RecipeProxy implements RecipeProvider {

    private RecipeReader recipeReader;
    private Recipe currentRecipe;

    public RecipeProxy(String filename) {
        this.recipeReader = new RecipeReader(filename);
    }

    @Override
    public Recipe getRecipeById(String id) throws Exception {
        if(currentRecipe == null || !currentRecipe.getId().equals(id)) {
            currentRecipe = recipeReader.getRecipeById(id);
        }
        return currentRecipe;
    }

    @Override
    public Recipe getRecipeByName(String name) throws Exception {
        if (currentRecipe == null || !currentRecipe.getName().equals(name)) {
            currentRecipe = recipeReader.getRecipeByName(name);
        }

        return currentRecipe;
    }

    @Override
    public void updateRecipe(Recipe recipe) throws Exception {
        recipeReader.updateRecipe(recipe);
    }

}
