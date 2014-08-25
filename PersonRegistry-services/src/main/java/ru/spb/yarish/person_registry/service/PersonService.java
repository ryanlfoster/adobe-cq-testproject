package ru.spb.yarish.person_registry.service;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spb.yarish.person_registry.dto.Person;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import java.util.ArrayList;
import java.util.List;

@Component(immediate = true)
@Service(PersonService.class)
public class PersonService {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    @Reference
    private ResourceResolverFactory resolverFactory;

    public boolean createPerson(Person person) {
        boolean result = false;
        Session session = null;
        try {
            //Invoke the adaptTo method to create a Session used to create a QueryManager
            ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
            session = resourceResolver.adaptTo(Session.class);

            //Create a node that represents the root node
            Node root = session.getRootNode();

            //Get the content node in the JCR
            Node content = root.getNode("content");

            //Determine if the content/person node exists

            Node personRoot;
            if (content.hasNode("person"))
                personRoot = content.getNode("person");
            else
                personRoot = content.addNode("person", "nt:unstructured");

            String nodeName = Long.toString(getUnusedId(personRoot));
            log.debug("Node id is {}", nodeName);
            Node personNode = personRoot.addNode(nodeName, "nt:unstructured");
            personNode.setProperty("name", person.getName());
            personNode.setProperty("lastName", person.getLastName());
            personNode.setProperty("description", person.getDescription());
            session.save();

            result = true;
        } catch (RepositoryException e) {
            log.error(e.getMessage(), e);
        } catch (LoginException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (session != null)
                session.logout();
        }
        log.debug("Probably person was created.");
        return result;
    }

    /**
     * Returns id of last personRoot child increased by 1.
     * May returns 0 if personRoot hasn't children.
     */
    private long getUnusedId(Node personRoot) throws RepositoryException {
        NodeIterator iterator = personRoot.getNodes();
        if (!iterator.hasNext())
            return 0;
        Node lastChild = null;
        while (iterator.hasNext()) {
            lastChild = iterator.nextNode();
        }
        return Long.valueOf(lastChild != null ? lastChild.getName() : null) + 1;
    }

    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<Person>();
        Session session = null;
        try {
            ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
            session = resourceResolver.adaptTo(Session.class);

            //Obtain the query manager for the session ...
            QueryManager queryManager = session.getWorkspace().getQueryManager();
            String sqlStatement = "SELECT * FROM [nt:unstructured] WHERE (ischildnode('/content/person/'))";
            Query query = queryManager.createQuery(sqlStatement, Query.JCR_SQL2);

            //Execute the query and get the results ...
            QueryResult result = query.execute();
            NodeIterator nodeIterator = result.getNodes();

            while (nodeIterator.hasNext()) {
                Node currentNode = nodeIterator.nextNode();
                Person currentPerson = new Person();
                if (currentNode.hasProperty("name"))
                    currentPerson.setName(currentNode.getProperty("name").getString());
                if (currentNode.hasProperty("lastName"))
                    currentPerson.setLastName(currentNode.getProperty("lastName").getString());
                if (currentNode.hasProperty("description"))
                    currentPerson.setDescription(currentNode.getProperty("description").getString());
                currentPerson.setId(Long.valueOf(currentNode.getName()));

                persons.add(currentPerson);
            }
        } catch (RepositoryException e) {
            log.error("RepositoryException: " + e);
        } catch (LoginException e) {
            log.error("RepositoryException: " + e);
        } finally {
            if (session != null)
                session.logout();
        }
        log.debug("{} persons were found successfully", persons.size());

        return persons;
    }

    public boolean deletePerson(String id) {
        log.debug("Deleting person with id: {}", id);
        boolean result = false;
        Session session = null;
        try {
            //Invoke the adaptTo method to create a Session used to create a QueryManager
            ResourceResolver resourceResolver = resolverFactory.getAdministrativeResourceResolver(null);
            session = resourceResolver.adaptTo(Session.class);

            //Create a node that represents the root node
            Node root = session.getRootNode();

            //Get the content node in the JCR
            Node person = root.getNode("content/person/" + id);
            person.remove();
            session.save();

            result = true;
        } catch (RepositoryException e) {
            log.error("RepositoryException: " + e);
        } catch (LoginException e) {
            log.error("RepositoryException: " + e);
        } finally {
            if (session != null)
                session.logout();
        }
        log.debug("Successfully deleted.");
        return result;
    }
}
