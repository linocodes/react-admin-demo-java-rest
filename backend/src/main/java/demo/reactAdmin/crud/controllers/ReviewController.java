package demo.reactAdmin.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactAdmin.rest.controllers.BaseController;
import demo.reactAdmin.crud.entities.Review;
import demo.reactAdmin.crud.repos.ReviewRepository;

@RestController
@RequestMapping("api/v1")
public class ReviewController extends BaseController<Review> {
    @Autowired
    private ReviewRepository repo;

    @RequestMapping(value = "reviews", method = RequestMethod.POST)
    public Review create(@RequestBody Review review) {
        return repo.save(review);
    }

    @RequestMapping(value = "reviews/{id}", method = RequestMethod.PUT)
    public Review update(@RequestBody Review review, @PathVariable int id) {
        review.id = id;
        return repo.save(review);
    }

    @RequestMapping(value = "reviews/{id}/published/{value}", method = RequestMethod.POST)
    public Review publishedUpdate(@PathVariable int id, @PathVariable boolean value) {
        Review review = repo.findOne(id);
        review.published = value;
        return repo.save(review);
    }

    @RequestMapping(value = "reviews/{id}", method = RequestMethod.GET)
    public Review getById(@PathVariable int id) {
        return repo.findOne(id);
    }

    @RequestMapping(value = "reviews", method = RequestMethod.GET)
    public Iterable<Review> filterBy(
            @RequestParam(required = false, name = "filter") String filterStr,
            @RequestParam(required = false, name = "range") String rangeStr, @RequestParam(required = false, name="sort") String sortStr) {
        return super.filterBy(filterStr,rangeStr, sortStr, repo);
    }
}
