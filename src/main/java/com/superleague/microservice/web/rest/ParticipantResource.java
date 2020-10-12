package com.superleague.microservice.web.rest;

import com.superleague.microservice.domain.Participant;
import com.superleague.microservice.repository.ParticipantRepository;
import com.superleague.microservice.repository.search.ParticipantSearchRepository;
import com.superleague.microservice.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.superleague.microservice.domain.Participant}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ParticipantResource {

    private final Logger log = LoggerFactory.getLogger(ParticipantResource.class);

    private static final String ENTITY_NAME = "superleagueParticipant";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParticipantRepository participantRepository;

    private final ParticipantSearchRepository participantSearchRepository;

    public ParticipantResource(ParticipantRepository participantRepository, ParticipantSearchRepository participantSearchRepository) {
        this.participantRepository = participantRepository;
        this.participantSearchRepository = participantSearchRepository;
    }

    /**
     * {@code POST  /participants} : Create a new participant.
     *
     * @param participant the participant to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new participant, or with status {@code 400 (Bad Request)} if the participant has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/participants")
    public ResponseEntity<Participant> createParticipant(@Valid @RequestBody Participant participant) throws URISyntaxException {
        log.debug("REST request to save Participant : {}", participant);
        if (participant.getId() != null) {
            throw new BadRequestAlertException("A new participant cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Participant result = participantRepository.save(participant);
        participantSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/participants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /participants} : Updates an existing participant.
     *
     * @param participant the participant to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated participant,
     * or with status {@code 400 (Bad Request)} if the participant is not valid,
     * or with status {@code 500 (Internal Server Error)} if the participant couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/participants")
    public ResponseEntity<Participant> updateParticipant(@Valid @RequestBody Participant participant) throws URISyntaxException {
        log.debug("REST request to update Participant : {}", participant);
        if (participant.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Participant result = participantRepository.save(participant);
        participantSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, participant.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /participants} : get all the participants.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of participants in body.
     */
    @GetMapping("/participants")
    public List<Participant> getAllParticipants() {
        log.debug("REST request to get all Participants");
        return participantRepository.findAll();
    }

    /**
     * {@code GET  /participants/:id} : get the "id" participant.
     *
     * @param id the id of the participant to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the participant, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/participants/{id}")
    public ResponseEntity<Participant> getParticipant(@PathVariable Long id) {
        log.debug("REST request to get Participant : {}", id);
        Optional<Participant> participant = participantRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(participant);
    }

    /**
     * {@code DELETE  /participants/:id} : delete the "id" participant.
     *
     * @param id the id of the participant to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/participants/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        log.debug("REST request to delete Participant : {}", id);
        participantRepository.deleteById(id);
        participantSearchRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/participants?query=:query} : search for the participant corresponding
     * to the query.
     *
     * @param query the query of the participant search.
     * @return the result of the search.
     */
    @GetMapping("/_search/participants")
    public List<Participant> searchParticipants(@RequestParam String query) {
        log.debug("REST request to search Participants for query {}", query);
        return StreamSupport
            .stream(participantSearchRepository.search(queryStringQuery(query)).spliterator(), false)
        .collect(Collectors.toList());
    }
}
